package pl.wipek.accounts.bonuses.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.exceptions.NoFactoryException;
import pl.wipek.accounts.bonuses.factory.AccountBonusesFactory;
import pl.wipek.accounts.bonuses.factory.AccountBonusesFactoryProvider;
import pl.wipek.accounts.bonuses.families.BonusContainer;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.accounts.ejb.dao.ActualVouchersDao;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.emails.EmailSender;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.mail.MessagingException;
import javax.naming.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//@Singleton
//@Startup
@Stateless
public class MonthlyAccountBonusesTimer {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyAccountBonusesTimer.class);

    private Set<Account> accounts;
    private Set<ActualVoucher> actualVouchers;

    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

    @EJB(beanInterface = ActualVouchersDao.class, beanName = "ActualVouchersDaoImpl")
    private ActualVouchersDao actualVouchersDao;

    @EJB
    private AccountBonusesFactoryProvider accountBonusesFactoryProvider;

    private Set<BonusContainer> bonusContainers = new HashSet<>();

    @PostConstruct
    private void getActualVouchers() {
        actualVouchers = this.actualVouchersDao.getAll();
        System.out.println(actualVouchers);
    }

//    @Schedules({
//            @Schedule(dayOfMonth = "1")
//    })
    public Set<BonusContainer> chargeMonthlyBonuses() {
        this.bonusContainers = new HashSet<>();
        accounts = accountsDao.getAll();
        this.clearBonuses();
        createFactories();
        return bonusContainers;
    }

    private void clearBonuses() {
    }

    private void createFactories() {
        logger.info("Creating bonuses for: " + accounts.size() + " accounts");
        accounts.parallelStream().forEach(this::prepareFactory);
    }

    private void prepareFactory(Account account) {
        AccountBonusesFactory accountBonusesFactory = null;
        try {
            accountBonusesFactory = accountBonusesFactoryProvider.getFactory(account);
            accountBonusesFactory.setAccount(account);
            accountBonusesFactory.setActualVouchers(actualVouchers);
            BonusContainer bonusContainer = createBonuses(accountBonusesFactory, account);
            bonusContainers.add(bonusContainer);
            logger.info("Account before update: " + account);
            Account updatedAccount = bonusContainer.saveBonuses();
            logger.info("Account after update: " + account);
            updateAccount(updatedAccount);
            bonusContainer.sendEmailWithBonuses();
        } catch (NoFactoryException e) {
            e.printStackTrace();
        }
    }

    private BonusContainer createBonuses(AccountBonusesFactory accountBonusesFactory, Account account) {
        BonusContainer bonusContainer = new BonusContainer();
        bonusContainer.setAccount(account);
        bonusContainer.setSaldoBonus(accountBonusesFactory.createSaldoBonus());
        logger.info("Saldo bonus: " + bonusContainer.getSaldoBonus().isGranted() + " value: " + bonusContainer.getSaldoBonus().getSaldo() + " for account " + bonusContainer.getAccount().getId());
        bonusContainer.setVoucherBonus(accountBonusesFactory.createVoucherBonus(bonusContainer.getSaldoBonus()));
        logger.info("Vouchers bonus: " + bonusContainer.getVoucherBonus().isGranted() + " value: " + bonusContainer.getVoucherBonus().getGrantedVouchers().toString() + " for account " + bonusContainer.getAccount().getId());
        bonusContainer.setMoneyBackBonus(accountBonusesFactory.createMoneyBackBonus(bonusContainer.getSaldoBonus()));
        logger.info("Moneyback bonus: " + bonusContainer.getMoneyBackBonus().isGranted() + " value: " + bonusContainer.getMoneyBackBonus().getGrantedBonus() + " for account " + bonusContainer.getAccount().getId());
        bonusContainer.setTransactionsBonus(accountBonusesFactory.createTransactionsBonus(bonusContainer.getMoneyBackBonus()));
        logger.info("Transactions bonus: " + bonusContainer.getTransactionsBonus().isGranted() + " value: " + bonusContainer.getTransactionsBonus().getTransactionBonuses().toString() + " for account " + bonusContainer.getAccount().getId());
        return bonusContainer;
    }

    private void updateAccount(Account account) {
//        try {
//            accountsDao.merge(account);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
    }

}

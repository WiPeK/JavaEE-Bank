package pl.wipek.accounts.bonuses.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.exceptions.NoFactoryException;
import pl.wipek.accounts.bonuses.factory.AccountBonusesFactory;
import pl.wipek.accounts.bonuses.factory.AccountBonusesFactoryProvider;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.emails.EmailSender;

import javax.ejb.*;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Set;

//@Singleton
//@Startup
@Stateless
public class MonthlyAccountBonusesTimer {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyAccountBonusesTimer.class);


    private Set<Account> accounts;
    private SaldoBonus saldoBonus;
    private VoucherBonus voucherBonus;
    private MoneyBackBonus moneyBackBonus;
    private TransactionsBonus transactionsBonus;

    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

//    @Schedules({
//            @Schedule(dayOfMonth = "1")
//    })
    public void chargeMonthlyBonuses() {
        accounts = accountsDao.getAll();
        this.clearBonuses();
        createFactories();
    }

    private void clearBonuses() {
        //todo
    }

    private void createFactories() {
        accounts.forEach((Account account) -> {
            try {
                AccountBonusesFactory accountBonusesFactory = AccountBonusesFactoryProvider.getFactory(account);
                accountBonusesFactory.setAccount(account);
                createBonuses(accountBonusesFactory);
                saveBonuses(account);
                if(isMockEmailList(account.getId())) {
                    sendEmailWithBonuses(account);
                }
            } catch (NoFactoryException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isMockEmailList(String accountId) {
        return accountId.equals("6075205366930CAAE050EDD4221D1C44") ||
                accountId.equals("6075205366940CAAE050EDD4221D1C44") ||
                accountId.equals("60F174307A77395DE050EDD4221D315E");
    }

    private void createBonuses(AccountBonusesFactory accountBonusesFactory) {
        this.saldoBonus = accountBonusesFactory.createSaldoBonus();
        this.voucherBonus = accountBonusesFactory.createVoucherBonus(saldoBonus);
        this.moneyBackBonus = accountBonusesFactory.createMoneyBackBonus(saldoBonus);
        this.transactionsBonus = accountBonusesFactory.createTransactionsBonus(moneyBackBonus);
    }

    private void saveBonuses(Account account) {
        saveSaldoBonus(account);
        saveMoneyBackBonus(account);
        saveVouchersBonus(account);
        saveTransactionsBonus(account);
    }

    private void saveSaldoBonus(Account account) {
        if (saldoBonus.isGranted()) {
            Double newBalance = account.getBalance() + saldoBonus.getSaldo();
            account.setLastMonthSaldo(account.getBalance());
            account.setBalance(newBalance);
            updateAccount(account);
        }
    }

    private void saveMoneyBackBonus(Account account) {
        if (moneyBackBonus.isGranted()) {
            Double newBalance = account.getBalance() + moneyBackBonus.getGrantedBonus();
            account.setBalance(newBalance);
            updateAccount(account);
        }
    }

    private void saveVouchersBonus(Account account) {
        if (voucherBonus.isGranted()) {
            account.setGrantedVouchers(voucherBonus.getGrantedVouchers());
            updateAccount(account);
        }
    }

    private void saveTransactionsBonus(Account account) {
        if (transactionsBonus.isGranted()) {
            TransactionBonus transactionBonus = transactionsBonus.getTransactionBonus();
            account.setTransactionBonus(transactionBonus);
            updateAccount(account);
        }
    }

    private void updateAccount(Account account) {
        try {
            account = accountsDao.merge(account);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void sendEmailWithBonuses(Account account) {
        String subject = "Otrzymane bonusy konta";
        StringBuilder message = new StringBuilder("Bonuses granted to account: " + account.getAccountNumber());
        String mailTo = "wipekxxx@gmail.com";

        message.append(saldoBonus.isGranted() ? ("Saldo bonus: " + saldoBonus.getSaldo() + "\n") : "");
        message.append(moneyBackBonus.isGranted() ? ("Money back bonus: " + moneyBackBonus.getGrantedBonus() + "\n") : "");
        message.append(transactionsBonus.isGranted() ?
                (
                        "Transaction bonus: \n" +
                                "Free payments: " + transactionsBonus.getTransactionBonus().getFreePayments() + "\n" +
                                "Free atm transactions: " + transactionsBonus.getTransactionBonus().getFreeAtmTransactions() + "\n" +
                                "Free premium payments: " + transactionsBonus.getTransactionBonus().getFreePremiumPayments() + "\n"
                ) : "");
        if (voucherBonus.isGranted()) {
            message.append("Granted vouchers: \n");
            for (GrantedVoucher grantedVoucher : voucherBonus.getGrantedVouchers()) {
                message.append(grantedVoucher.getActualVoucher().getName()).append(" kod: ").append(grantedVoucher.getCode()).append("\n");
            }
        }
        logger.info(message.toString());
        try {
            EmailSender.sendEmail(subject, message.toString(), mailTo);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }


    }
}

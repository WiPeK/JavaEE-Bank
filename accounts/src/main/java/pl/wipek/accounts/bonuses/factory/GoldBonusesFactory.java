package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.moneyback.GoldMoneyBackBonus;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.GoldSaldoBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.GoldTransactionsBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.GoldVoucherBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;

import javax.ejb.EJB;
import javax.inject.Named;
import java.util.Set;

@Named
public class GoldBonusesFactory implements AccountBonusesFactory {
    private Account account;

    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

    private Set<ActualVoucher> actualVouchers;

    @Override
    public void setActualVouchers(Set<ActualVoucher> actualVouchers) {
        this.actualVouchers = actualVouchers;
    }

    @Override
    public MoneyBackBonus createMoneyBackBonus(SaldoBonus saldoBonus) {
        GoldMoneyBackBonus goldMoneyBackBonus = new GoldMoneyBackBonus();
        goldMoneyBackBonus.setBlikUseNumbers(accountsDao.countBlikUses(account));
        goldMoneyBackBonus.setMaxPaymentToAccount(accountsDao.countPaymentsToAccount(account));
        goldMoneyBackBonus.setMobileLoggingNumbers(accountsDao.countMobileLogging(account));
        goldMoneyBackBonus.setAccount(account);
        goldMoneyBackBonus.addBonus();
        return goldMoneyBackBonus;
    }

    @Override
    public TransactionsBonus createTransactionsBonus(MoneyBackBonus moneyBackBonus) {
        GoldTransactionsBonus goldTransactionsBonus = new GoldTransactionsBonus();
        goldTransactionsBonus.setAtmPayments(accountsDao.countAtmPayments(account));
        goldTransactionsBonus.setCardPayments(accountsDao.countCardPayments(account));
        goldTransactionsBonus.setCardTransactions(accountsDao.countCardPayments(account));
        goldTransactionsBonus.setSumAtmTransactions(accountsDao.sumAtmPayments(account));
        goldTransactionsBonus.setSumCardTransactions(accountsDao.sumCardPayments(account));
        goldTransactionsBonus.setSumPaymentsToAccount(accountsDao.countPaymentsToAccount(account));
        goldTransactionsBonus.setAccount(account);
        goldTransactionsBonus.addBonus();
        return goldTransactionsBonus;
    }

    @Override
    public SaldoBonus createSaldoBonus() {
        GoldSaldoBonus goldSaldoBonus = new GoldSaldoBonus(account);
        goldSaldoBonus.addSaldoBonus();
        return goldSaldoBonus;
    }

    @Override
    public VoucherBonus createVoucherBonus(SaldoBonus saldoBonus) {
        GoldVoucherBonus goldVoucherBonus = new GoldVoucherBonus(account);
        if(saldoBonus.isGranted()) {
            goldVoucherBonus.setActualVouchers(actualVouchers);
            goldVoucherBonus.createPremiumVouchers(saldoBonus.getSaldo());
            goldVoucherBonus.grandVouchers(saldoBonus.getSaldo());
        }

        return goldVoucherBonus;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }
}

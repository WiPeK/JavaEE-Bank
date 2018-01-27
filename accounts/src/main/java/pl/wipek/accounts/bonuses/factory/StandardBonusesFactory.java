package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.moneyback.StandardMoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.saldo.StandardSaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.StandardTransactionsBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.StandardVoucherBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Named
public class StandardBonusesFactory implements AccountBonusesFactory {

    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

    private Account account;

    private Set<ActualVoucher> actualVouchers;

    @Override
    public MoneyBackBonus createMoneyBackBonus(SaldoBonus saldoBonus) {
        StandardMoneyBackBonus standardMoneyBackBonus = new StandardMoneyBackBonus();
        standardMoneyBackBonus.setBlikUseNumbers(accountsDao.countBlikUses(account));
        standardMoneyBackBonus.setMaxPaymentToAccount(accountsDao.countPaymentsToAccount(account));
        standardMoneyBackBonus.setMobileLoggingNumbers(accountsDao.countMobileLogging(account));
        standardMoneyBackBonus.setAccount(account);
        standardMoneyBackBonus.addBonus();
        return standardMoneyBackBonus;
    }

    @Override
    public TransactionsBonus createTransactionsBonus(MoneyBackBonus moneyBackBonus) {
        StandardTransactionsBonus standardTransactionsBonus = new StandardTransactionsBonus();
        standardTransactionsBonus.setAccount(account);
        standardTransactionsBonus.addBonus();
        return standardTransactionsBonus;
    }

    @Override
    public SaldoBonus createSaldoBonus() {
        StandardSaldoBonus standardSaldoBonus = new StandardSaldoBonus(account);
        standardSaldoBonus.addSaldoBonus();
        System.out.println(standardSaldoBonus.isGranted() + " " + standardSaldoBonus.getSaldo());
        return standardSaldoBonus;
    }

    @Override
    public VoucherBonus createVoucherBonus(SaldoBonus saldoBonus) {
        StandardVoucherBonus standardVoucherBonus = new StandardVoucherBonus(account);
        if(saldoBonus.isGranted()) {
            standardVoucherBonus.setActualVouchers(actualVouchers);
            standardVoucherBonus.grandVouchers(saldoBonus.getSaldo());
        }

        return standardVoucherBonus;
    }

    @Override
    public void setAccount(@NotNull Account account) {
        this.account = account;
    }

    @Override
    public void setActualVouchers(@NotNull Set<ActualVoucher> actualVouchers) {
        this.actualVouchers = actualVouchers;
    }
}

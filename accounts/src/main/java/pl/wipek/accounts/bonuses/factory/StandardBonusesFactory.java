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
import pl.wipek.accounts.ejb.dao.ActualVouchersDao;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.accounts.ejb.finder.EjbFinder;

import javax.naming.NamingException;

public class StandardBonusesFactory implements AccountBonusesFactory {

    private AccountsDAO accountsDao;
    private ActualVouchersDao actualVouchersDao;

    private Account account;

    public StandardBonusesFactory() {
        try {
            accountsDao = (AccountsDAO) EjbFinder.getBean("AccountsDaoImpl");
            actualVouchersDao = (ActualVouchersDao)EjbFinder.getBean("ActualVouchersDaoImpl");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

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
        return null;
    }

    @Override
    public SaldoBonus createSaldoBonus() {
        StandardSaldoBonus standardSaldoBonus = new StandardSaldoBonus(account);
        standardSaldoBonus.addSaldoBonus();
        return standardSaldoBonus;
    }

    @Override
    public VoucherBonus createVoucherBonus(SaldoBonus saldoBonus) {
        StandardVoucherBonus standardVoucherBonus = new StandardVoucherBonus(account);
        if(saldoBonus.isGranted()) {
            standardVoucherBonus.setActualVouchers(actualVouchersDao.getAll());
            standardVoucherBonus.grandVouchers(saldoBonus.getSaldo());
        }

        return standardVoucherBonus;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }
}

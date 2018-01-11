package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.moneyback.BusinessMoneyBackBonus;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.BusinessSaldoBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.BusinessTransactionsBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.BusinessVoucherBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.accounts.ejb.dao.ActualVouchersDao;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.accounts.ejb.finder.EjbFinder;

import javax.naming.NamingException;

public class BusinessBonusesFactory implements AccountBonusesFactory {
    private AccountsDAO accountsDao;
    private ActualVouchersDao actualVouchersDao;

    private Account account;

    public BusinessBonusesFactory() {
        try {
            accountsDao = (AccountsDAO) EjbFinder.getBean("AccountsDaoImpl");
            actualVouchersDao = (ActualVouchersDao)EjbFinder.getBean("ActualVouchersDaoImpl");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MoneyBackBonus createMoneyBackBonus(SaldoBonus saldoBonus) {
        BusinessMoneyBackBonus businessMoneyBackBonus = new BusinessMoneyBackBonus();
        businessMoneyBackBonus.setBlikUseNumbers(accountsDao.countBlikUses(account));
        businessMoneyBackBonus.setMaxPaymentToAccount(accountsDao.countPaymentsToAccount(account));
        businessMoneyBackBonus.setMobileLoggingNumbers(accountsDao.countMobileLogging(account));
        businessMoneyBackBonus.setMoneySpentForFuel(accountsDao.countMoneySpentForFuel(account));
        businessMoneyBackBonus.setAccount(account);
        businessMoneyBackBonus.addBonus();
        return businessMoneyBackBonus;
    }

    @Override
    public TransactionsBonus createTransactionsBonus(MoneyBackBonus moneyBackBonus) {
        BusinessTransactionsBonus businessTransactionsBonus = new BusinessTransactionsBonus();
        businessTransactionsBonus.setAccount(account);
        businessTransactionsBonus.addBonus();
        return businessTransactionsBonus;
    }

    @Override
    public SaldoBonus createSaldoBonus() {
        BusinessSaldoBonus businessSaldoBonus = new BusinessSaldoBonus(account);
        businessSaldoBonus.addBonusForTransactions(accountsDao.countLastMonthTransactions(account) >= 100);
        businessSaldoBonus.addSaldoBonus();
        return businessSaldoBonus;
    }

    @Override
    public VoucherBonus createVoucherBonus(SaldoBonus saldoBonus) {
        BusinessVoucherBonus businessVoucherBonus = new BusinessVoucherBonus(account);
        if(saldoBonus.isGranted()) {
            businessVoucherBonus.setActualVouchers(actualVouchersDao.getAll());
            businessVoucherBonus.setPremiumVouchersForZusTransfers(accountsDao.countZusTransfers(account.getId()));
            businessVoucherBonus.grandVouchers(saldoBonus.getSaldo());
        }

        return businessVoucherBonus;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }
}

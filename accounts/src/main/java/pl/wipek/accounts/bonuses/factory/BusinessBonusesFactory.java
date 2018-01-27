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
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;

import javax.ejb.EJB;
import javax.inject.Named;
import java.util.Set;

@Named
public class BusinessBonusesFactory implements AccountBonusesFactory {
    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

    private Account account;

    private Set<ActualVoucher> actualVouchers;

    @Override
    public void setActualVouchers(Set<ActualVoucher> actualVouchers) {
        this.actualVouchers = actualVouchers;
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
            businessVoucherBonus.setActualVouchers(actualVouchers);
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

package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.shared.domain.entity.Account;

public interface AccountBonusesFactory {
    public SaldoBonus createSaldoBonus();
    public MoneyBackBonus createMoneyBackBonus(SaldoBonus saldoBonus);
    public TransactionsBonus createTransactionsBonus(MoneyBackBonus moneyBackBonus);
    public VoucherBonus createVoucherBonus(SaldoBonus saldoBonus);
    public void setAccount(Account account);
}

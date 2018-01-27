package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;

import javax.validation.constraints.NotNull;
import java.util.Set;

public interface AccountBonusesFactory {
    public SaldoBonus createSaldoBonus();
    public MoneyBackBonus createMoneyBackBonus(@NotNull SaldoBonus saldoBonus);
    public TransactionsBonus createTransactionsBonus(@NotNull MoneyBackBonus moneyBackBonus);
    public VoucherBonus createVoucherBonus(@NotNull SaldoBonus saldoBonus);
    public void setAccount(@NotNull Account account);
    public void setActualVouchers(@NotNull Set<ActualVoucher> actualVouchers);
}

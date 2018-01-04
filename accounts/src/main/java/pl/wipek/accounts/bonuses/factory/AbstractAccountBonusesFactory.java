package pl.wipek.accounts.bonuses.factory;

import pl.wipek.accounts.bonuses.families.card.CardBonus;
import pl.wipek.accounts.bonuses.families.depositinterest.DepositInterestBonus;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;

public interface AbstractAccountBonusesFactory {
    public MoneyBackBonus createMoneyBackBonus();
    public DepositInterestBonus createDepositInterestBonus(SaldoBonus saldoBonus);
    public SaldoBonus createSaldoBonus();
    public TransactionsBonus createTransactionsBonus(CardBonus cardBonus);
    public CardBonus createCardBonus();
    public VoucherBonus createVoucherBonus(TransactionsBonus transactionsBonus);
}

package pl.wipek.accounts.bonuses.families.saldo;

import pl.wipek.shared.domain.entity.Account;

public class BusinessSaldoBonus extends SaldoBonus {
    private static Double BONUS = 100.0;
    private static Double BONUS_FOR_100_TRANSACTIONS = 100.0;

    private boolean isBonusTransactionGranted = false;

    public BusinessSaldoBonus(Account account) {
        super(account);
    }

    @Override
    public void addSaldoBonus() {
        if(getSaldo() > MIN_SALDO_FOR_BUSINESS_ACCOUNT) {
            grantedBonus = BONUS;
            grantedBonus += isBonusTransactionGranted ? BONUS_FOR_100_TRANSACTIONS : 0.0;
            setGranted(true);
        }
    }

    public void addBonusForTransactions(boolean addBonus) {
        this.isBonusTransactionGranted = addBonus;
    }
}

package pl.wipek.accounts.bonuses.families.saldo;

import pl.wipek.shared.domain.entity.Account;

public class StandardSaldoBonus extends SaldoBonus {
    private static Double BONUS = 10.0;

    public StandardSaldoBonus(Account account) {
        super(account);
    }

    @Override
    public void addSaldoBonus() {
        if(getSaldo() > MIN_SALDO_FOR_STANDARD_ACCOUNT) {
            grantedBonus = BONUS;
            setGranted(true);
        }
    }
}

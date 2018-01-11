package pl.wipek.accounts.bonuses.families.saldo;

import pl.wipek.shared.domain.entity.Account;

import java.util.Random;

public class GoldSaldoBonus extends SaldoBonus {
    private static Double MIN_BONUS = 10.0;
    private static Double MAX_BONUS = 100.0;

    public GoldSaldoBonus(Account account) {
        super(account);
    }

    @Override
    public void addSaldoBonus() {
        if(getSaldo() > MIN_SALDO_FOR_GOLD_ACCOUNT) {
            Random random = new Random();
            Double bonus = MIN_BONUS + (MAX_BONUS - MIN_BONUS) * random.nextDouble();
            grantedBonus = Double.longBitsToDouble(Math.round(bonus));
            setGranted(true);
        }
    }
}

package pl.wipek.accounts.bonuses.families.moneyback;

import java.util.Random;

public class GoldMoneyBackBonus extends MoneyBackBonus {
    private static int MIN_MOBILE_LOGGINS_NUMBERS = 1;
    private static int MIN_BLIK_USE_NUMBERS = 1;
    private static Double MIN_SUM_PAYMENTS_TO_ACCOUNT = 2000.0;
    private static Double MIN_BONUS = 50.0;
    private static Double MAX_BONUS = 50.0;

    @Override
    public void addBonus() {
        if(isConditionsChecked()) {
            Random random = new Random();Double bonus = MIN_BONUS + (MAX_BONUS - MIN_BONUS) * random.nextDouble();
            grantedBonus = Double.longBitsToDouble(Math.round(bonus));

            setGranted(true);
        }
    }

    private boolean isConditionsChecked() {
        return  mobileLoggingNumbers >= MIN_MOBILE_LOGGINS_NUMBERS &&
                blikUseNumbers >= MIN_BLIK_USE_NUMBERS &&
                sumPaymentToAccount >= MIN_SUM_PAYMENTS_TO_ACCOUNT;
    }

}

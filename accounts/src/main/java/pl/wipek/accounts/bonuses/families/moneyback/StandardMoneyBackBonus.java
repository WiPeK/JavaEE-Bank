package pl.wipek.accounts.bonuses.families.moneyback;

public class StandardMoneyBackBonus extends MoneyBackBonus {
    private static int MIN_MOBILE_LOGGINS_NUMBERS = 5;
    private static int MIN_BLIK_USE_NUMBERS = 3;
    private static Double MIN_SUM_PAYMENTS_TO_ACCOUNT = 1000.0;
    private static Double BONUS = 50.0;

    @Override
    public void addBonus() {
        if(isConditionsChecked()) {
            setGrantedBonus(BONUS);
            setGranted(true);
        }
    }

    private boolean isConditionsChecked() {
        return  getMobileLoggingNumbers() >= MIN_MOBILE_LOGGINS_NUMBERS &&
                getBlikUseNumbers() >= MIN_BLIK_USE_NUMBERS &&
                getSumPaymentToAccount() >= MIN_SUM_PAYMENTS_TO_ACCOUNT;
    }
}

package pl.wipek.accounts.bonuses.families.moneyback;

public class BusinessMoneyBackBonus extends MoneyBackBonus {
    private static int MIN_MOBILE_LOGGINS_NUMBERS = 1;
    private static int MIN_BLIK_USE_NUMBERS = 1;
    private static Double MIN_SUM_PAYMENTS_TO_ACCOUNT = 2000.0;
    private static Double MIN_MONEY_SPENT_FOR_FUEL = 300.0;
    private static Double BONUS = 100.0;
    private Double moneySpentForFuel;

    @Override
    public void addBonus() {
        if(isConditionsChecked()) {
            grantedBonus = BONUS * (sumPaymentToAccount / 10000);
            setGranted(true);
        }
    }

    public void setMoneySpentForFuel(Double moneySpentForFuel) {
        this.moneySpentForFuel = moneySpentForFuel;
    }

    private boolean isConditionsChecked() {
        return  mobileLoggingNumbers >= MIN_MOBILE_LOGGINS_NUMBERS &&
                blikUseNumbers >= MIN_BLIK_USE_NUMBERS &&
                sumPaymentToAccount >= MIN_SUM_PAYMENTS_TO_ACCOUNT &&
                moneySpentForFuel >= MIN_MONEY_SPENT_FOR_FUEL;
    }
}

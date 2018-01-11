package pl.wipek.accounts.bonuses.families.moneyback;

import pl.wipek.shared.domain.entity.Account;

public abstract class MoneyBackBonus {
    private boolean isGranted;
    private Account account;

    protected int mobileLoggingNumbers = 0;

    protected Double sumPaymentToAccount = 0.0;
    protected int blikUseNumbers = 0;
    protected Double grantedBonus;

    public abstract void addBonus();

    public void setMobileLoggingNumbers(int mobileLoggingNumbers) {
        this.mobileLoggingNumbers = mobileLoggingNumbers;
    }

    public void setMaxPaymentToAccount(Double sumPaymentToAccount) {
        this.sumPaymentToAccount = sumPaymentToAccount;
    }

    public void setBlikUseNumbers(int blikUseNumbers) {
        this.blikUseNumbers = blikUseNumbers;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public Double getGrantedBonus() {
        return grantedBonus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isGranted() {
        return isGranted;
    }
}

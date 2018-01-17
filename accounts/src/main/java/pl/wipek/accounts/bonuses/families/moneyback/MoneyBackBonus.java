package pl.wipek.accounts.bonuses.families.moneyback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wipek.shared.domain.entity.Account;

import javax.xml.bind.annotation.XmlTransient;

public abstract class MoneyBackBonus {
    private boolean isGranted;
    @XmlTransient
    @JsonIgnore
    private Account account;

    private int mobileLoggingNumbers = 0;

    private Double sumPaymentToAccount = 0.0;
    private int blikUseNumbers = 0;
    private Double grantedBonus;

    public int getMobileLoggingNumbers() {
        return mobileLoggingNumbers;
    }

    public Double getSumPaymentToAccount() {
        return sumPaymentToAccount;
    }

    public int getBlikUseNumbers() {
        return blikUseNumbers;
    }

    public abstract void addBonus();

    protected void setGrantedBonus(Double grantedBonus) {
        this.grantedBonus = grantedBonus;
    }

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

    @JsonIgnore
    @XmlTransient
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

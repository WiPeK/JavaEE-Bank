package pl.wipek.accounts.bonuses.families.saldo;

import pl.wipek.shared.domain.entity.Account;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;


//saldo teraz - saldo z przed miesiaca
public abstract class SaldoBonus {
    protected static Double MIN_SALDO_FOR_STANDARD_ACCOUNT = 1500.0;
    protected static Double MIN_SALDO_FOR_GOLD_ACCOUNT = 1200.0;
    protected static Double MIN_SALDO_FOR_BUSINESS_ACCOUNT = 10000.0;

    private boolean isGranted = false;
    private Double saldo = 0.0;
    @XmlTransient
    protected Account account;
    protected Double grantedBonus;

    public SaldoBonus(@NotNull Account account) {
        this.account = account;
        saldo = (Math.round((account.getBalance() - account.getLastMonthSaldo())*100)/100) * 1.0;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public abstract void addSaldoBonus();

    public Double getGrantedBonus() {
        return grantedBonus;
    }

    public void setGrantedBonus(Double grantedBonus) {
        this.grantedBonus = grantedBonus;
    }
}

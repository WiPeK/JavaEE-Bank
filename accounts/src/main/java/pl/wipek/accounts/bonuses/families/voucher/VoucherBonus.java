package pl.wipek.accounts.bonuses.families.voucher;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;
import pl.wipek.shared.ejb.dao.Dao;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public abstract class VoucherBonus {
    protected static int MAX_VOUCHERS_FOR_STANDARD_ACCOUNT = 1;
    protected static int MAX_VOUCHERS_FOR_GOLD_ACCOUNT = 3;
    protected static int MAX_VOUCHERS_FOR_BUSINESS_ACCOUNT = 10;

    protected static Double MIN_SALDO_FOR_STANDARD_ACCOUNT = 1000.0;
    protected static Double MIN_SALDO_FOR_GOLD_ACCOUNT = 4000.0;
    protected static Double MIN_SALDO_FOR_BUSINESS_ACCOUNT = 20000.0;

    @XmlTransient
    protected Account account;

    @XmlElement(name = "grantedVouchers")
    protected Set<GrantedVoucher> grantedVouchers = new HashSet<>();
    protected Set<ActualVoucher> actualVouchers = new HashSet<>();

    protected boolean isGranted = true;

    public VoucherBonus(Account account) {
        this.account = account;
    }

    protected String generateVoucherCode(ActualVoucher actualVoucher) {
        return Base64.getEncoder().encodeToString(actualVoucher.toString().getBytes());
    }

    public void setActualVouchers(Set<ActualVoucher> actualVouchers) {
        this.actualVouchers = actualVouchers;
    }

    public abstract void grandVouchers(Double saldo);

    public Set<GrantedVoucher> getGrantedVouchers() {
        return grantedVouchers;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void save(Dao dao) {
        grantedVouchers.forEach(dao::persist);
    }

    @Override
    public String toString() {
        return "VoucherBonus{" +
                "account=" + account +
                ", grantedVouchers=" + grantedVouchers.toString() +
                ", actualVouchers=" + actualVouchers.toString() +
                ", isGranted=" + isGranted +
                '}';
    }
}

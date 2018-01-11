package pl.wipek.shared.domain.entity.account.bonuses;

import pl.wipek.shared.domain.entity.Account;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "GRANTED_VOUCHERS")
@XmlRootElement
public class GrantedVoucher implements Serializable {

    private String id;
    private String code;
    private Account account;
    private ActualVoucher actualVoucher;

    public GrantedVoucher() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "ACTUAL_VOUCHERS_ID")
    @XmlTransient
    public ActualVoucher getActualVoucher() {
        return actualVoucher;
    }

    public void setActualVoucher(ActualVoucher actualVoucher) {
        this.actualVoucher = actualVoucher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantedVoucher voucher = (GrantedVoucher) o;
        return Objects.equals(id, voucher.id) &&
                Objects.equals(code, voucher.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "GrantedVoucher{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

package pl.wipek.shared.domain.entity.account.bonuses;

import org.hibernate.annotations.GenericGenerator;
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
    @GenericGenerator(name = "db-uuid", strategy = "guid")
    @GeneratedValue(generator = "db-uuid")
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

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTUAL_VOUCHERS_ID")
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

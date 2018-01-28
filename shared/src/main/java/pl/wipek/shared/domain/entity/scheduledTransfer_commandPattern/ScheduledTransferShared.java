package pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.wipek.shared.domain.entity.Account;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "scheduled_Transfer")
@XmlRootElement
public class ScheduledTransferShared implements Serializable {

    @Id
    @Column(name = "transferID", nullable = false)
    private String id;

    @Column(name = "trans_state", nullable = false)
    private String state;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Column(name = "Money", nullable = false)
    private String balance;

    @Column(name = "BeneficaryIBAN", nullable = false)
    private String beneficiaryIBAN;

    @Column(name = "trans_date", nullable = false)
    private Date date;

    @JsonIgnore
    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountID")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledTransferShared that = (ScheduledTransferShared) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (IBAN != null ? !IBAN.equals(that.IBAN) : that.IBAN != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (beneficiaryIBAN != null ? !beneficiaryIBAN.equals(that.beneficiaryIBAN) : that.beneficiaryIBAN != null)
            return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (IBAN != null ? IBAN.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (beneficiaryIBAN != null ? beneficiaryIBAN.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBeneficiaryIBAN() {
        return beneficiaryIBAN;
    }

    public void setBeneficiaryIBAN(String beneficiaryIBAN) {
        this.beneficiaryIBAN = beneficiaryIBAN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "ScheduledTransferShared{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", balance='" + balance + '\'' +
                ", beneficiaryIBAN='" + beneficiaryIBAN + '\'' +
                ", date=" + date +
                '}';
    }
}

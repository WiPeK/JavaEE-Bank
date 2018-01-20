package pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "Sch_Transfer")
@XmlRootElement
public class Transfer implements Serializable {

    @Id
    @Column(name = "transferID", nullable = false)
    private String id;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Column(name = "Money", nullable = false)
    private String balance;

    @Column(name = "BeneficaryIBAN", nullable = false)
    private String beneficiaryIBAN;

    @Column(name = "trans_date", nullable = false)
    private Date date;

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

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer transfer = (Transfer) o;

        if (!id.equals(transfer.id)) return false;
        if (!IBAN.equals(transfer.IBAN)) return false;
        if (!balance.equals(transfer.balance)) return false;
        if (!beneficiaryIBAN.equals(transfer.beneficiaryIBAN)) return false;
        return date != null ? date.equals(transfer.date) : transfer.date == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + IBAN.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + beneficiaryIBAN.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }


}

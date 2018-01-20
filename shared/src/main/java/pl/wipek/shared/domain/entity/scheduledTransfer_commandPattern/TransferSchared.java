package pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "Sch_Transfer")
@XmlRootElement
public class TransferSchared implements Serializable {

    @Id
    @Column(name = "transferID", nullable = false)
    private String transferId;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Column(name = "Money", nullable = false)
    private String balance;

    @Column(name = "BeneficaryIBAN", nullable = false)
    private String beneficiaryIBAN;

    @Column(name = "trans_date", nullable = false)
    private Date date;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "sh_transferID")
    ScheduledTransferShared scheduledTransferShared;

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

    @Override
    public String toString() {
        return "TransferSchared{" +
                "transferId='" + transferId + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", balance='" + balance + '\'' +
                ", beneficiaryIBAN='" + beneficiaryIBAN + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferSchared that = (TransferSchared) o;

        if (!transferId.equals(that.transferId)) return false;
        if (!IBAN.equals(that.IBAN)) return false;
        if (!balance.equals(that.balance)) return false;
        if (!beneficiaryIBAN.equals(that.beneficiaryIBAN)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = transferId.hashCode();
        result = 31 * result + IBAN.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + beneficiaryIBAN.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    public String getTransferId() {

        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }
}

package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Entity
@Table(name = "DOMESTIC_BENEFICIARIES")
@XmlRootElement
public class Beneficiary implements Serializable {
    private String id;
    private String name;
    private String address;
    private String accountNumber;
    @XmlTransient
    @JsonIgnore
    private DomesticTransfer domesticTransfer;

    public Beneficiary() {
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

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "ACCOUNT_NUMBER")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @XmlTransient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "DOMESTIC_TRANSFER_ID")
    public DomesticTransfer getDomesticTransfer() {
        return domesticTransfer;
    }

    public void setDomesticTransfer(DomesticTransfer domesticTransfer) {
        this.domesticTransfer = domesticTransfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beneficiary that = (Beneficiary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, name, address, accountNumber);
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
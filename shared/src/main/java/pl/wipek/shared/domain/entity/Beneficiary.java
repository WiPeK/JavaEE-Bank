package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Entity
@XmlRootElement
public class Beneficiary implements Serializable {
    private String idBeneficiaries;
    private String name;
    private String address;
    private String accountNumber;

    @SuppressWarnings("unused")
    @Id
    @JsonProperty
    public String getIdBeneficiaries() {
        return idBeneficiaries;
    }

    public void setIdBeneficiaries(String idBeneficiaries) {
        this.idBeneficiaries = idBeneficiaries;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beneficiary)) return false;

        Beneficiary that = (Beneficiary) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return accountNumber != null ? accountNumber.equals(that.accountNumber) : that.accountNumber == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

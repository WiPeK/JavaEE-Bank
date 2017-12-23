package pl.wipek.shared.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DOMESTIC_TRANSFERS")
@XmlRootElement
public class DomesticTransfer implements Serializable {

    private String id;
    private Account account;
    private Set<DomesticBeneficiary> domesticBeneficiaries = new HashSet<>();

    public DomesticTransfer() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @XmlTransient
    @OneToMany(mappedBy = "domesticTransfer", targetEntity = DomesticBeneficiary.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<DomesticBeneficiary> getDomesticBeneficiaries() {
        return domesticBeneficiaries;
    }

    public void setDomesticBeneficiaries(Set<DomesticBeneficiary> domesticBeneficiaries) {
        this.domesticBeneficiaries = domesticBeneficiaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomesticTransfer that = (DomesticTransfer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DomesticTransfer{" +
                "id='" + id + '\'' +
                '}';
    }
}

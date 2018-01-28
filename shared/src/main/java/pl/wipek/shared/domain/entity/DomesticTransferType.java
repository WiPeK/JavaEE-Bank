package pl.wipek.shared.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Entity
@XmlRootElement
@Table(name = "DOMESTIC_TRANSFER_TYPES")
public class DomesticTransferType implements Serializable {
    public static String[] PREMIUM_TRANSFERS = {"Express Elixir", "Sorbnet", "Blue Cash"};

    private String id;
    private String value;
    private Double cost;
    @XmlTransient
    @JsonIgnore
    private Set<DomesticTransfer> domesticTransfers = new HashSet<>();

    public DomesticTransferType() {
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

    @Column(name = "VALUE", nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "COST", nullable = false)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "type", targetEntity = DomesticTransfer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<DomesticTransfer> getDomesticTransfers() {
        return domesticTransfers;
    }

    public void setDomesticTransfers(Set<DomesticTransfer> domesticTransfers) {
        this.domesticTransfers = domesticTransfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomesticTransferType that = (DomesticTransferType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value, cost);
    }

    @Override
    public String toString() {
        return "DomesticTransferType{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", cost=" + cost +
                '}';
    }
}

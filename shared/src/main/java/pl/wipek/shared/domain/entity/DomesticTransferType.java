package pl.wipek.shared.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Entity
@XmlRootElement
@Table(name = "DOMESTIC_TRANSFER_TYPES")
public class DomesticTransferType {
    private String id;
    private String value;

    public DomesticTransferType() {
    }

    @Id
    @Column(name = "ID", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomesticTransferType)) return false;

        DomesticTransferType that = (DomesticTransferType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DomesticTransferType{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

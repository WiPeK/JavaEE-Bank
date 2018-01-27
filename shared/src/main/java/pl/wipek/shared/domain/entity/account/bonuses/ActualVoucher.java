package pl.wipek.shared.domain.entity.account.bonuses;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACTUAL_VOUCHERS")
@XmlRootElement
public class ActualVoucher {

    private String id;
    private String name;
    private Set<GrantedVoucher> grantedVouchers;

    public ActualVoucher() {
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

    @OneToMany(mappedBy = "actualVoucher", targetEntity = GrantedVoucher.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlTransient
    public Set<GrantedVoucher> getGrantedVouchers() {
        return grantedVouchers;
    }

    public void setGrantedVouchers(Set<GrantedVoucher> grantedVouchers) {
        this.grantedVouchers = grantedVouchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActualVoucher that = (ActualVoucher) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ActualVoucher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


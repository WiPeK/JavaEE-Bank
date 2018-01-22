package pl.wipek.shared.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CURRENCIES")
public class Currency  implements Serializable{
    private String id;
    private String name;
    private String code;
    private Set<Account> accounts;

    public Currency() {
    }

    @Id
    @Column(name = "ID")
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

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    @OneToMany(mappedBy = "currency", targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) &&
                Objects.equals(name, currency.name) &&
                Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
@XmlRootElement
public class Customer extends User {
    private String id;
    private Set<Account> accounts = new HashSet<>();

    public Customer() {
    }

    @Id
    @Override
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    @OneToMany(mappedBy = "customer", targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                "login='" + super.getLogin() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

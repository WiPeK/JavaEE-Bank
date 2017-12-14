package pl.wipek.shared.domain.entity;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends User {
    private String id;
    private List<Account> accounts = new ArrayList<>();

    public Customer() {
    }

    @Id
    @Type(type = "objectid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ElementCollection
    @IndexedEmbedded
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Column(name = "login")
    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Column(name = "password")
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                "login='" + super.getLogin() + '\'' +
                ", accounts=" + accounts.isEmpty() +
                '}';
    }
}

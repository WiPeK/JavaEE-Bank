package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends User {
    private String id;
//    private Set<Account> accounts = new HashSet<>();

    public Customer() {
    }

    @Id
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    public Set<Account> getAccounts() {
//        return accounts;
//    }

//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }

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
//                ", accounts=" + accounts +
                '}';
    }
}

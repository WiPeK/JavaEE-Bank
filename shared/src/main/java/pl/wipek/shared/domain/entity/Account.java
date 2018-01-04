package pl.wipek.shared.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
public class Account implements Serializable {

    private String id;
    private String accountNumber;
    private Double balance;
    private String name;
    private Double blockedAmount;
    private Customer customer;
    private Currency currency;
    private Set<DomesticTransfer> domesticTransfers;

    private String type;

    public Account() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_NUMBER")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "BALANCE")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "BLOCKED_AMOUNT")
    public Double getBlockedAmount() {
        return blockedAmount;
    }

    public void setBlockedAmount(Double blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    @XmlTransient
    @OneToMany(mappedBy = "account", targetEntity = DomesticTransfer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<DomesticTransfer> getDomesticTransfers() {
        return domesticTransfers;
    }

    public void setDomesticTransfers(Set<DomesticTransfer> domesticTransfers) {
        this.domesticTransfers = domesticTransfers;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (accountNumber != null ? !accountNumber.equals(account.accountNumber) : account.accountNumber != null)
            return false;
        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        return blockedAmount != null ? blockedAmount.equals(account.blockedAmount) : account.blockedAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (blockedAmount != null ? blockedAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccounts='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", blockedAmount=" + blockedAmount +
                ", type=" + type +
                '}';
    }
}

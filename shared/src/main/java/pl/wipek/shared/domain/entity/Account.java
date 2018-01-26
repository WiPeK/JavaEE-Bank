package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
public class Account implements Serializable {
    public static final String STANDARD_TYPE = "standard";
    public static final String GOLD_TYPE = "gold";
    public static final String BUSINESS_TYPE = "business";

    private String id;
    private String accountNumber;
    private Double balance;
    private String name;
    private Double blockedAmount;
    private Double lastMonthSaldo;
    private Customer customer;
    private Currency currency;
    private Set<TransactionBonus> transactionBonuses;
    private Set<DomesticTransfer> domesticTransfers;
    private Set<GrantedVoucher> grantedVouchers;

    private String type;

    public Account() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GenericGenerator(name = "db-uuid", strategy = "guid")
    @GeneratedValue(generator = "db-uuid")
    @XmlElement(name = "id")
    @JsonProperty(value = "id")
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
        this.balance = Math.round(balance * 100) / 100.0;
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

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "LAST_MONTH_SALDO")
    public Double getLastMonthSaldo() {
        return lastMonthSaldo;
    }

    public void setLastMonthSaldo(Double lastMonthSaldo) {
        this.lastMonthSaldo = lastMonthSaldo;
    }

    @XmlTransient
    @OneToMany(mappedBy = "account", targetEntity = GrantedVoucher.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<GrantedVoucher> getGrantedVouchers() {
        return grantedVouchers;
    }

    public void setGrantedVouchers(Set<GrantedVoucher> grantedVouchers) {
        this.grantedVouchers = grantedVouchers;
    }

    @XmlTransient
    @OneToMany(mappedBy = "account", targetEntity = TransactionBonus.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<TransactionBonus> getTransactionBonuses() {
        return transactionBonuses;
    }

    public void setTransactionBonuses(Set<TransactionBonus> transactionBonuses) {
        this.transactionBonuses = transactionBonuses;
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
                ", name='" + name + '\'' +
                ", blockedAmount=" + blockedAmount +
                ", type=" + type +
                ", last month saldo=" + lastMonthSaldo +
                '}';
    }
}

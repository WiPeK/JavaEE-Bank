package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Entity
@XmlRootElement
public class Account implements Serializable {
    private String idAccounts;
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
    private String name;
    private BigDecimal blockedAmount;
    private Users user;

    @Id
    @Column(name = "ID_ACCOUNTS", nullable = false)
    @JsonProperty
    public String getIdAccounts() {
        return idAccounts;
    }

    public void setIdAccounts(String idAccounts) {
        this.idAccounts = idAccounts;
    }

    @JsonProperty
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 32)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty
    @Column(name = "BALANCE", nullable = true)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @JsonProperty
    @Column(name = "CURRENCY", nullable = true)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty
    @Column(name = "NAME", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    @Column(name = "BLOCKED_AMOUNT", nullable = true)
    public BigDecimal getBlockedAmount() {
        return blockedAmount;
    }

    public void setBlockedAmount(BigDecimal blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (idAccounts != null ? !idAccounts.equals(account.idAccounts) : account.idAccounts != null) return false;
        if (accountNumber != null ? !accountNumber.equals(account.accountNumber) : account.accountNumber != null)
            return false;
        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (currency != null ? !currency.equals(account.currency) : account.currency != null) return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        return blockedAmount != null ? blockedAmount.equals(account.blockedAmount) : account.blockedAmount == null;
    }

    @Override
    public int hashCode() {
        int result = idAccounts != null ? idAccounts.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (blockedAmount != null ? blockedAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccounts='" + idAccounts + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", blockedAmount=" + blockedAmount +
                '}';
    }
}

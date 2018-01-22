package pl.wipek.shared.domain.entity.account.bonuses;

import org.hibernate.annotations.GenericGenerator;
import pl.wipek.shared.domain.entity.Account;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "TRANSACTION_BONUS")
public class TransactionBonus {
    private String id;
    private int freePayments;
    private int freeAtmTransactions;
    private int freePremiumPayments;
    private Account account;

    public TransactionBonus() {
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

    @Column(name = "FREE_PAYMENTS", nullable = false)
    public int getFreePayments() {
        return freePayments;
    }

    public void setFreePayments(int freePayments) {
        this.freePayments = freePayments;
    }

    @Column(name = "FREE_ATM_TRANSACTIONS", nullable = false)
    public int getFreeAtmTransactions() {
        return freeAtmTransactions;
    }

    public void setFreeAtmTransactions(int freeAtmTransactions) {
        this.freeAtmTransactions = freeAtmTransactions;
    }

    @Column(name = "FREE_PREMIUM_PAYMENTS", nullable = false)
    public int getFreePremiumPayments() {
        return freePremiumPayments;
    }

    public void setFreePremiumPayments(int freePremiumPayments) {
        this.freePremiumPayments = freePremiumPayments;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

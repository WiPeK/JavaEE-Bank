package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

public abstract class TransactionsBonus {
    private boolean isGranted;
    @XmlElement(name = "transactionBonuses")
    protected Set<TransactionBonus> transactionBonuses = new HashSet<>();

    @XmlTransient
    private Account account;

    public abstract void addBonus();

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public Set<TransactionBonus> getTransactionBonuses() {
        return transactionBonuses;
    }

    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "TransactionsBonus{" +
                "isGranted=" + isGranted +
                ", transactionBonuses=" + transactionBonuses.toString() +
                ", account=" + account +
                '}';
    }
}

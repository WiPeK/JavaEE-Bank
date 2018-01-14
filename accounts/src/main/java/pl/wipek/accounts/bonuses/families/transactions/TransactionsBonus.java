package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

import java.util.HashSet;
import java.util.Set;

public abstract class TransactionsBonus {
    private boolean isGranted;
    protected Set<TransactionBonus> transactionBonuses = new HashSet<>();
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

    public void setTransactionBonuses(Set<TransactionBonus> transactionBonuses) {
        this.transactionBonuses = transactionBonuses;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

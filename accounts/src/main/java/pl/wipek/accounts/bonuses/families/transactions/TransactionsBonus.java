package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

public abstract class TransactionsBonus {
    private boolean isGranted;
    protected TransactionBonus transactionBonus;
    private Account account;

    public abstract void addBonus();

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public TransactionBonus getTransactionBonus() {
        return transactionBonus;
    }

    public void setTransactionBonus(TransactionBonus transactionBonus) {
        this.transactionBonus = transactionBonus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

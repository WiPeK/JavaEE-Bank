package pl.wipek.accounts.bonuses.families.transactions;

public class StandardTransactionsBonus extends TransactionsBonus {
    @Override
    public void addBonus() {
        transactionBonus.setFreeAtmTransactions(0);
        transactionBonus.setFreePayments(0);
        transactionBonus.setFreePremiumPayments(0);
        setGranted(false);
    }
}

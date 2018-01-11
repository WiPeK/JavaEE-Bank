package pl.wipek.accounts.bonuses.families.transactions;

public class BusinessTransactionsBonus extends TransactionsBonus {
    private static int PREMIUM_PAYMENTS = 10;
    private static int FREE_PAYMENTS = 10;
    private static int FREE_ATM_TRANSACTIONS = 10;

    @Override
    public void addBonus() {
        transactionBonus.setFreeAtmTransactions(FREE_ATM_TRANSACTIONS);
        transactionBonus.setFreePayments(FREE_PAYMENTS);
        transactionBonus.setFreePremiumPayments(PREMIUM_PAYMENTS);
        setGranted(true);
    }
}

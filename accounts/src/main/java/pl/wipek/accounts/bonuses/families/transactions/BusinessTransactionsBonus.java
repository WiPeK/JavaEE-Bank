package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

public class BusinessTransactionsBonus extends TransactionsBonus {
    private static int PREMIUM_PAYMENTS = 10;
    private static int FREE_PAYMENTS = 10;
    private static int FREE_ATM_TRANSACTIONS = 10;

    @Override
    public void addBonus() {
        TransactionBonus transactionBonus = new TransactionBonus();
        transactionBonus.setFreeAtmTransactions(FREE_ATM_TRANSACTIONS);
        transactionBonus.setFreePayments(FREE_PAYMENTS);
        transactionBonus.setFreePremiumPayments(PREMIUM_PAYMENTS);
        transactionBonuses.add(transactionBonus);
        setGranted(true);
    }
}

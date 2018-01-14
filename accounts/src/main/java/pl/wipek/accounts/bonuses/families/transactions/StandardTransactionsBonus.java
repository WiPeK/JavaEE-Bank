package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

public class StandardTransactionsBonus extends TransactionsBonus {
    @Override
    public void addBonus() {
        TransactionBonus transactionBonus = new TransactionBonus();
        transactionBonus.setFreeAtmTransactions(0);
        transactionBonus.setFreePayments(0);
        transactionBonus.setFreePremiumPayments(0);
        transactionBonuses.add(transactionBonus);
        setGranted(false);
    }
}

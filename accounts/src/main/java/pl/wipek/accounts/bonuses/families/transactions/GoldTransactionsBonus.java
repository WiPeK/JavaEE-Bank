package pl.wipek.accounts.bonuses.families.transactions;

import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

public class GoldTransactionsBonus extends TransactionsBonus {
    private static int PREMIUM_PAYMENTS = 3;
    private static int FREE_PAYMENTS = 10;
    private static int FREE_ATM_TRANSACTIONS = 10;

    private static Double MIN_PAYMENTS_SUM = 50000.0;
    private static Double MIN_ATM_SUM = 1000.0;
    private static Double MIN_CARD_SUM = 5000.0;

    private static int MIN_CARD_TRANSACTIONS = 10;
    private static int MIN_TRANSACTIONS = 25;
    private static int MIN_ATM_TRANSACTIONS = 10;

    private Double sumPaymentsToAccount;
    private Double sumAtmTransactions;
    private Double sumCardTransactions;

    private int cardPayments;
    private int atmPayments;
    private int cardTransactions;

    @Override
    public void addBonus() {
        if (isConditionsChecked()) {
            TransactionBonus transactionBonus = new TransactionBonus();
            transactionBonus.setFreeAtmTransactions(FREE_ATM_TRANSACTIONS);
            transactionBonus.setFreePremiumPayments(PREMIUM_PAYMENTS);
            transactionBonus.setFreePayments(FREE_PAYMENTS);
            transactionBonuses.add(transactionBonus);
            setGranted(true);
        }
    }

    private boolean isConditionsChecked() {
        return  sumPaymentsToAccount >= MIN_PAYMENTS_SUM &&
                sumAtmTransactions >= MIN_ATM_SUM &&
                sumCardTransactions >= MIN_CARD_SUM &&
                cardPayments >= MIN_CARD_TRANSACTIONS &&
                atmPayments >= MIN_ATM_TRANSACTIONS &&
                cardTransactions >= MIN_TRANSACTIONS;
    }

    public void setSumPaymentsToAccount(Double sumPaymentsToAccount) {
        this.sumPaymentsToAccount = sumPaymentsToAccount;
    }

    public void setSumAtmTransactions(Double sumAtmTransactions) {
        this.sumAtmTransactions = sumAtmTransactions;
    }

    public void setSumCardTransactions(Double sumCardTransactions) {
        this.sumCardTransactions = sumCardTransactions;
    }

    public void setCardPayments(int cardPayments) {
        this.cardPayments = cardPayments;
    }

    public void setAtmPayments(int atmPayments) {
        this.atmPayments = atmPayments;
    }

    public void setCardTransactions(int cardTransactions) {
        this.cardTransactions = cardTransactions;
    }
}

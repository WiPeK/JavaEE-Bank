package pl.wipek.accounts.bonuses.families;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.families.moneyback.MoneyBackBonus;
import pl.wipek.accounts.bonuses.families.saldo.SaldoBonus;
import pl.wipek.accounts.bonuses.families.transactions.TransactionsBonus;
import pl.wipek.accounts.bonuses.families.voucher.VoucherBonus;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;

import java.util.Optional;
import java.util.Set;

public class BonusContainer {
    private static final Logger logger = LoggerFactory.getLogger(BonusContainer.class);

    private SaldoBonus saldoBonus;
    private VoucherBonus voucherBonus;
    private MoneyBackBonus moneyBackBonus;
    private TransactionsBonus transactionsBonus;

    private Account account;

    public SaldoBonus getSaldoBonus() {
        return saldoBonus;
    }

    public void setSaldoBonus(SaldoBonus saldoBonus) {
        this.saldoBonus = saldoBonus;
    }

    public VoucherBonus getVoucherBonus() {
        return voucherBonus;
    }

    public void setVoucherBonus(VoucherBonus voucherBonus) {
        this.voucherBonus = voucherBonus;
    }

    public MoneyBackBonus getMoneyBackBonus() {
        return moneyBackBonus;
    }

    public void setMoneyBackBonus(MoneyBackBonus moneyBackBonus) {
        this.moneyBackBonus = moneyBackBonus;
    }

    public TransactionsBonus getTransactionsBonus() {
        return transactionsBonus;
    }

    public void setTransactionsBonus(TransactionsBonus transactionsBonus) {
        this.transactionsBonus = transactionsBonus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account saveBonuses() {
        saveSaldoBonus();
        saveMoneyBackBonus();
        saveVouchersBonus();
        saveTransactionsBonus();
        return getAccount();
    }

    private void saveSaldoBonus() {
        if (saldoBonus.isGranted()) {
            Double newBalance = account.getBalance() + saldoBonus.getSaldo();
            account.setLastMonthSaldo(account.getBalance());
            account.setBalance(newBalance);
        }
    }

    private void saveMoneyBackBonus() {
        if (moneyBackBonus.isGranted()) {
            Double newBalance = account.getBalance() + moneyBackBonus.getGrantedBonus();
            account.setBalance(newBalance);
        }
    }

    private void saveVouchersBonus() {
        if (voucherBonus.isGranted()) {
            account.setGrantedVouchers(voucherBonus.getGrantedVouchers());
        }
    }

    private void saveTransactionsBonus() {
        if (transactionsBonus.isGranted()) {
            Set<TransactionBonus> transactionBonuses = transactionsBonus.getTransactionBonuses();
            account.setTransactionBonuses(transactionBonuses);
        }
    }

    public void sendEmailWithBonuses() {
        String subject = "Otrzymane bonusy konta";
        StringBuilder message = new StringBuilder("Bonuses granted to account: " + account.getAccountNumber());
        String mailTo = "wipekxxx@gmail.com";

        message.append(saldoBonus.isGranted() ? ("Saldo bonus: " + saldoBonus.getSaldo() + "\n") : "");
        message.append(moneyBackBonus.isGranted() ? ("Money back bonus: " + moneyBackBonus.getGrantedBonus() + "\n") : "");
        if (transactionsBonus.isGranted()) {
            Optional<TransactionBonus> transactionBonus = transactionsBonus.getTransactionBonuses().stream().findFirst();
            message.append(transactionBonus.map(transactionBonus1 -> (
                    "Transaction bonus: \n" +
                            "Free payments: " + transactionBonus1.getFreePayments() + "\n" +
                            "Free atm transactions: " + transactionBonus1.getFreeAtmTransactions() + "\n" +
                            "Free premium payments: " + transactionBonus1.getFreePremiumPayments() + "\n"
            )).orElse(""));
        }
        if (voucherBonus.isGranted()) {
            message.append("Granted vouchers: \n");
            for (GrantedVoucher grantedVoucher : voucherBonus.getGrantedVouchers()) {
                message.append(grantedVoucher.getActualVoucher().getName()).append(" kod: ").append(grantedVoucher.getCode()).append("\n");
            }
        }
        logger.info(message.toString());
//        try {
//            EmailSender.sendEmail(subject, message.toString(), mailTo);
//        } catch (IOException | MessagingException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String toString() {
        return "BonusContainer{" +
                "saldoBonus=" + saldoBonus +
                ", voucherBonus=" + voucherBonus +
                ", moneyBackBonus=" + moneyBackBonus +
                ", transactionsBonus=" + transactionsBonus +
                ", account=" + account +
                '}';
    }
}

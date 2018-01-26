package pl.wipek.payments.transfers.exceptions;

public class NoMoneyInAccountException extends Exception {
    public NoMoneyInAccountException() {
        super("No money in account");
    }
}

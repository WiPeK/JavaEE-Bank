package main.java;

/**
 * Created by Michał on 20.01.2018.
 */
public class AccountDirector {
    private final AccountBuilder accountBuilder;

    public AccountDirector(AccountBuilder accountBuilder) {

        this.accountBuilder = accountBuilder;
    }


    public UsedAccount getAccount() {
        return this.accountBuilder.getAccount();
    }

    public void build() {

        this.accountBuilder.buildIBAN();
        this.accountBuilder.buildInterst();
        this.accountBuilder.buildCurrency();
        this.accountBuilder.buildMobileToken();
        this.accountBuilder.buildInsurance();


    }
}
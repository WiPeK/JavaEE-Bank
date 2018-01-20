package main.java;

/**
 * Created by Michał on 20.01.2018.
 */
public interface AccountBuilder {
    public void buildIBAN();
    public void buildCurrency();
    public void buildInterst();
    public void buildInsurance();
    public void buildMobileToken();
    public UsedAccount getAccount();


}

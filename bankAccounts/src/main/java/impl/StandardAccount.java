package main.java.impl;

import main.java.AccountBuilder;
import main.java.UsedAccount;

/**
 * Created by Michał on 20.01.2018.
 */
public class StandardAccount implements AccountBuilder {
    UsedAccount standardAccount = new UsedAccount();
    @Override
    public void buildIBAN() {

    }

    @Override
    public void buildCurrency() {

    }

    @Override
    public void buildInterst() {

    }

    @Override
    public void buildInsurance() {

    }

    @Override
    public void buildMobileToken() {

    }

    @Override
    public UsedAccount getAccount() {
        return this.standardAccount;
    }
}
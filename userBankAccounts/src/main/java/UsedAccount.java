package main.java;

/**
 * Created by Michał on 20.01.2018.
 */
public class UsedAccount {
    private System AccountID;
    private String IBAN;
    private String Inerst;
    private String Insurance;
    private String MobileToken;
    private String Currency;


    public void setCurrency(String currency) {
        this.Currency = currency;
    }


    public void setInerst(String inerst) {
        Inerst = inerst;
    }



    public void setInsurance(String insurance) {
        Insurance = insurance;
    }



    public void setMobileToken(String mobileToken) {
        MobileToken = mobileToken;
    }


    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}

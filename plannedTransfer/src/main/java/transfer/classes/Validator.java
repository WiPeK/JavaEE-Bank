package transfer.classes;

import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */
public class Validator {

    private String state;
    private String IBan;
    private String beneficiaryIBan;
    private String money;
    private Date date;

    public boolean inCorrectNumber(String newIBAN){
        //zapytanie czy uzytkownik posiada dany adres konta bankowego
        return false;
    }

    public boolean isCorrectBeneficiaryNumber(String newBeneficiaryIBAN){
        return false;
    }

    public boolean isCorrectDate(Date newDate){
        return date.after(newDate);
    }

    public boolean haveMoney(String newAmount){

        Double newMoney = Double.parseDouble(newAmount);
        Double money = Double.parseDouble(this.money);

        return money <= newMoney ? true : false;

    }

    public boolean isNotInProgress(){

        return state != "Progress" ? true : false;

    }

}

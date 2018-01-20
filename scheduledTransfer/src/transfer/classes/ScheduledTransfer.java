package transfer.classes;

import transfer.interfaces.Transfer;

import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */
public class ScheduledTransfer  implements Transfer {

    Validator validator = new Validator();
    String newMoney;
    String newIBAN;
    String newBeneficiaryIBAN;
    Date date;

    public void changeAmount() {
        validator.haveMoney(newMoney);

        //jakas podmiana w bazie danych
    }

    public void cancelTransfer() {
        validator.isNotInProgress();
        //usuniecie przelewu
    }

    public void changeBeneficary() {
        validator.isCorrectBeneficiaryNumber(newBeneficiaryIBAN);
    }

    public void changeDate() {
        validator.isCorrectDate(date);
    }

    public void changeSender(){
        validator.inCorrectNumber(newIBAN);
    }
}

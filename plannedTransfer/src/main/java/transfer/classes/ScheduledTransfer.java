package transfer.classes;


import ejb.services.TransferService;
import transfer.interfaces.Transfer;

import javax.ejb.EJB;
import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */
public class ScheduledTransfer  implements Transfer {

    @EJB
    TransferService transferService;

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

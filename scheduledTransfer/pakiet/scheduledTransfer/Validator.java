package pakiet.scheduledTransfer;

/**
 * Created by Michał on 20.01.2018.
 */
public class Validator {

    private String State;
    private String IBan;
    private String BeneficiaryIBan;
    private String Money;

    public boolean inCorrectNumber(){

        return false;
    }

    public boolean isCorrectBeneficiaryNumber(){
        // chuj wie jak to zrobic
        return false;
    }

    public boolean haveMoney(String newAmount){

        return Money != newAmount? true : false;

    }

    public boolean isNotInProgress(){

        return State != "Progress" ? true : false;

    }

}

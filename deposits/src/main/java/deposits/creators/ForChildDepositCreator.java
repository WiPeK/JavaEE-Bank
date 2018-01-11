package deposits.creators;

import deposits.Deposit;
import deposits.ForChildDeposit;
import deposits.creators.DepositCreator;
import deposits.ejb.Validators.Exceptions.WrongDateException;


import javax.ejb.Stateless;


@Stateless(name = "ForChildDepositCreator", mappedName = "ForChildDepositCreator")
public class ForChildDepositCreator extends DepositCreator {

    @Override
    public Deposit createDeposit() throws WrongDateException {

        return new ForChildDeposit();
    }
}

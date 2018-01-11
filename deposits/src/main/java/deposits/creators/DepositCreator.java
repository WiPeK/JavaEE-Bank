package deposits.creators;

import deposits.Deposit;
import deposits.ejb.Validators.Exceptions.WrongDateException;

public abstract class DepositCreator {
    public abstract Deposit createDeposit() throws WrongDateException;
}

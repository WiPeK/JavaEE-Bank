package deposits.ejb.Validators;

import deposits.ejb.Validators.Exceptions.WrongDateException;

import java.util.Date;

public interface DepositDataValidator {
    public boolean checkDataIsValid (Date EndOfDepositDate) throws WrongDateException;
}

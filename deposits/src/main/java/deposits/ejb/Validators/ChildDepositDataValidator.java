package deposits.ejb.Validators;

import deposits.ejb.Validators.Exceptions.WrongDateException;

import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class ChildDepositDataValidator implements DepositDataValidator {

    Instant currentTime;

    public ChildDepositDataValidator() {
        this.currentTime = Instant.now();
    }

    @Override
    public boolean checkDataIsValid(Date EndOfDepositData) throws WrongDateException {
        if (currentTime.isAfter(EndOfDepositData.toInstant())) {
            throw new WrongDateException("PastTimeException");
        }
        if (EndOfDepositData.toInstant().isAfter(currentTime.plus(365, DAYS))) {
            return true;
        } else {
            return false;
        }
    }
}

package deposits.ejb;

import deposits.ForChildDeposit;
import deposits.ejb.Validators.Exceptions.WrongDateException;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;

import java.util.Date;


public class ForChildDepositTest {

    @Test
    public void shouldCreateObject() throws WrongDateException {
        System.out.println(new Date().toString());

            new ForChildDeposit(1L, "Adam", "Adam", new Date(new Date().getTime()+ 1000000), new Date(), new BigDecimal(80000), "PLN");
        }


    @Test(expected = WrongDateException.class)
    public void shouldThrowException() throws WrongDateException{
        try {
            System.out.println(new ForChildDeposit(1L, "Adam", "Adam", new Date(new Date().getTime() - 1000L), new Date(), new BigDecimal(80000), "PLN").toString());
        }catch (WrongDateException ex){
            System.out.println(ex);
        }
    }

}

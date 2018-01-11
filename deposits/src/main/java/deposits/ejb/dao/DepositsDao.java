package deposits.ejb.dao;

import deposits.Deposit;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

public interface DepositsDao extends Dao<String, Deposit> {
    public Set<Deposit> getUserDeposits(String customerId);
}

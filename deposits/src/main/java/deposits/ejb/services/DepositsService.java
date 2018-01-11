package deposits.ejb.services;

import deposits.Deposit;
import deposits.ejb.dao.DepositsDao;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

@Stateless
public class DepositsService implements Serializable{
    @EJB(beanInterface = DepositsDao.class, beanName = "DepositsDaoImpl")
    private DepositsDao depositsDao;
    public Set<Deposit> getUserDeposits(String customerId){
        return depositsDao.getUserDeposits(customerId);
    }
}

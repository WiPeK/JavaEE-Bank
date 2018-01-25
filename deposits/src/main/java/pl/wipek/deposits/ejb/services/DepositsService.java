package pl.wipek.deposits.ejb.services;

import javassist.NotFoundException;
import pl.wipek.deposits.ejb.dao.DepositsDao;
import pl.wipek.shared.domain.entity.Deposit;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

@Stateless
public class DepositsService implements Serializable{
    @EJB(beanInterface = DepositsDao.class, beanName = "DepositsDaoImpl")
    private DepositsDao depositsDao;

    public Deposit getDeposits(String depositId) throws NotFoundException{
            return depositsDao.findById(depositId);
    }

    public Deposit saveDeposit(Deposit deposit) throws DaoException{
        return depositsDao.persist(deposit);
    }

}

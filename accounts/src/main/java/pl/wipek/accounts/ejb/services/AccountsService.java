package pl.wipek.accounts.ejb.services;

import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Stateless
public class AccountsService implements Serializable {

    @EJB(beanInterface = AccountsDAO.class, beanName = "AccountsDaoImpl")
    private AccountsDAO accountsDao;

    public Set<Account> getUserAccounts(String customerId) {
       return accountsDao.getUserAccounts(customerId);
    }

    public Account createNewAccount(Account account) {
        try {
            return accountsDao.persist(account);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Set<Account> getAll(){
        return accountsDao.getAll();
    }
}

package pl.wipek.accounts.ejb.services;

import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;

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

    public Set<Account> getUserAccounts(String userId) {
       return accountsDao.getUserAccounts(userId);
    }
}

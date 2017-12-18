package pl.wipek.accounts.ejb.dao.impl;

import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Stateless(name = "AccountsDaoImpl")
public class AccountsDaoImpl extends AbstractDao<String, Account> implements AccountsDAO {

    @Override
    public Set<Account> getUserAccounts(String customerId) {
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.customer.id=:id")
                .setParameter("id", customerId);
        System.out.println(query);
        return new HashSet<>(query.getResultList());
    }
}

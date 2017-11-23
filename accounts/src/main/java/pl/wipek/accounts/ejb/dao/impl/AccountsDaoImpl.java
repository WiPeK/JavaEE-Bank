package pl.wipek.accounts.ejb.dao.impl;

import org.mockito.Mock;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Stateless(name = "AccountsDaoImpl")
public class AccountsDaoImpl extends AbstractDao<String, Account> implements AccountsDAO {

    @Mock
    private EntityManager entityManager;

    @Override
    public Set<Account> getUserAccounts(String userId) {
//        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(entityClass);
//        final Root<Account> root = criteriaQuery.from(entityClass);
//        criteriaQuery.where(criteriaBuilder.equal(root.get("user"), userId));
//        List<Account> res = getEntityManager().createQuery(criteriaQuery).getResultList();
        //return new HashSet<>(res);
        return getMockedAccounts();
    }

    private Set<Account> getMockedAccounts() {
        Set<Account> accounts = new HashSet<>();

        Account account1 = new Account();
        account1.setIdAccounts("E667E6C480394F45BC377D3F2DCFA4FT");
        account1.setAccountNumber("PL831050171937392025842606809009");
        account1.setBalance(new BigDecimal(1234.56));
        account1.setCurrency("PLN");
        account1.setName("NORMAL");

        Account account2 = new Account();
        account2.setIdAccounts("ZX67E6C480394F45BC377D3F2DCFA4B9");
        account2.setAccountNumber("EU709195080496737562156108531824");
        account2.setBalance(new BigDecimal(1234.56));
        account2.setCurrency("EUR");
        account2.setName("VIP");

        Account account3 = new Account();
        account3.setIdAccounts("E667E6C480394F4TYC377D3F2DCFA4B9");
        account3.setAccountNumber("US560958676315792527370876707478");
        account3.setBalance(new BigDecimal(1234.56));
        account3.setCurrency("USD");
        account3.setName("<20");

        Account account4 = new Account();
        account4.setIdAccounts("E667E6C4FT394F45BC377D3F2DCFA4B9");
        account4.setAccountNumber("PL129253072017537338784286639998");
        account4.setBalance(new BigDecimal(1234.56));
        account4.setCurrency("PLN");
        account4.setName("NORMAL");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        return accounts;
    }
}

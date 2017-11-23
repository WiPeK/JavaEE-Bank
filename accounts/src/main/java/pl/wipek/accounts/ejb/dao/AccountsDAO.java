package pl.wipek.accounts.ejb.dao;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
public interface AccountsDAO extends Dao<String, Account> {
    public Set<Account> getUserAccounts(String userId);
}

package pl.wipek.accounts.ejb.dao;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
public interface AccountsDAO extends Dao<String, Account> {
    public Set<Account> getUserAccounts(String customerId);
    public int countZusTransfers(String accountId);
    public int countLastMonthTransactions(Account account);
    public int countBlikUses(Account account);
    public int countMobileLogging(Account account);
    public Double countPaymentsToAccount(Account account);
    public Double countMoneySpentForFuel(Account account);
    public Double sumCardPayments(Account account);
    public int countCardPayments(Account account);
    public int countPayments(Account account);
    public int countAtmPayments(Account account);
    public Double sumAtmPayments(Account account);
}

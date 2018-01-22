package pl.wipek.accounts.ejb.dao.impl;

import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Stateless(name = "AccountsDaoImpl", mappedName = "AccountsDaoImpl")
public class AccountsDaoImpl extends AbstractDao<String, Account> implements AccountsDAO {

    @Override
    public Set<Account> getUserAccounts(String customerId) {
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.customer.id=:id")
                .setParameter("id", customerId);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public int countZusTransfers(String accountId) {
        return new Random().nextInt(1);
    }

    @Override
    public int countLastMonthTransactions(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 200;
    }

    @Override
    public int countBlikUses(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 10;
    }

    @Override
    public int countMobileLogging(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 10;
    }

    @Override
    public Double countPaymentsToAccount(Account account) {
        Double min = 1000.0;
        Double max = 100000.0;
        Double result = min + (max - min) * new Random().nextDouble();
        return Double.longBitsToDouble(Math.round(result));
    }

    @Override
    public Double countMoneySpentForFuel(Account account) {
        Double min = 100.0;
        Double max = 10000.0;
        Double result = min + (max - min) * new Random().nextDouble();
        return Double.longBitsToDouble(Math.round(result));
    }

    @Override
    public Double sumCardPayments(Account account) {
        Double min = 10.0;
        Double max = 10000.0;
        Double result = min + (max - min) * new Random().nextDouble();
        return Double.longBitsToDouble(Math.round(result));
    }

    @Override
    public int countCardPayments(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 20;
    }

    @Override
    public int countPayments(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 50;
    }

    @Override
    public int countAtmPayments(Account account) {
        return (new Random().nextInt() & Integer.MAX_VALUE) % 20;
    }

    @Override
    public Double sumAtmPayments(Account account) {
        Double min = 1.0;
        Double max = 20000.0;
        Double result = min + (max - min) * new Random().nextDouble();
        return Double.longBitsToDouble(Math.round(result));
    }


}

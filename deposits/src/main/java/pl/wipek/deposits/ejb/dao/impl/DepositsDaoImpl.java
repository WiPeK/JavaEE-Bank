package pl.wipek.deposits.ejb.dao.impl;

import pl.wipek.deposits.ejb.dao.DepositsDao;
import pl.wipek.shared.domain.entity.Deposit;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "DepositsDaoImpl", mappedName = "DepositsDaoImpl")
public class DepositsDaoImpl extends AbstractDao<String, Deposit> implements DepositsDao {


    public Set<Deposit> getDeposits(String depositId) {
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.id=:id")
                .setParameter("id", depositId);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Deposit> getAll() {
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName());
        return new HashSet<>(query.getResultList());
    }
}

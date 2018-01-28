package pl.wipek.loans.ejb.dao.impl;

import pl.wipek.loans.ejb.dao.LoansDao;
import pl.wipek.shared.domain.entity.Loan;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Stateless(name = "LoansDaoImpl", mappedName = "LoansDaoImpl")
public class LoansDaoImpl extends AbstractDao<String, Loan> implements LoansDao {


    public Set<Loan> getLoans(String loanId) {
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.id=:id")
                .setParameter("id", loanId);
        return new HashSet<>(query.getResultList());
    }
}

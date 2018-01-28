package pl.wipek.loans.ejb.services;

import javassist.NotFoundException;
import pl.wipek.loans.ejb.dao.LoansDao;
import pl.wipek.shared.domain.entity.Loan;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

@Stateless
public class LoansService implements Serializable{
    @EJB(beanInterface = LoansDao.class, beanName = "LoansDaoImpl")
    private LoansDao loansDao;

    public Loan getLoans(String loanId) throws NotFoundException{
            return loansDao.findById(loanId);
    }

    public Set<Loan> getAllLoans() throws NotFoundException{
        return loansDao.getAll();
    }

    public Loan saveLoan(Loan loan) throws DaoException{
        return loansDao.persist(loan);
    }

}

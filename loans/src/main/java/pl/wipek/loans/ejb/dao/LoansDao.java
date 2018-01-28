package pl.wipek.loans.ejb.dao;

import pl.wipek.shared.domain.entity.Loan;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

public interface LoansDao extends Dao<String,Loan>{
    public Set<Loan> getLoans(String loansId);
    public Set<Loan> getAll();
}

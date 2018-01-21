package pl.wipek.deposits.ejb.dao;

import pl.wipek.shared.domain.entity.Deposit;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

public interface DepositsDao extends Dao<String,Deposit>{
    public Set<Deposit> getDeposits(String depositsId);
}

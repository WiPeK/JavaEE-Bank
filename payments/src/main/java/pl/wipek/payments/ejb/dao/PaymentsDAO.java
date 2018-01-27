package pl.wipek.payments.ejb.dao;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;
import pl.wipek.shared.ejb.dao.Dao;

import javax.persistence.NoResultException;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
public interface PaymentsDAO extends Dao<String, DomesticTransferType> {
    public Set<DomesticTransferType> getDomesticPaymentTypes();
    public String getCustomerEmailByAccountId(String accountId);
    public Account getActualAccount(String accountId);
    public DomesticTransferType getDomesticTransferTypeById(String id) throws NoResultException;
    public TransactionBonus getTransactionBonus(String accountId) throws NoResultException;
    public void updateTransactionBonus(TransactionBonus transactionBonus);
    public void updateAccount(Account account);
}

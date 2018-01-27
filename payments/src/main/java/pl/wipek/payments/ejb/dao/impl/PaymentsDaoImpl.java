package pl.wipek.payments.ejb.dao.impl;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.domain.entity.account.bonuses.TransactionBonus;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless(name = "PaymentsDaoImpl")
public class PaymentsDaoImpl extends AbstractDao<String, DomesticTransferType> implements PaymentsDAO {

    @Override
    public Set<DomesticTransferType> getDomesticPaymentTypes() {
        return getAll();
    }

    @Override
    public String getCustomerEmailByAccountId(String accountId) {
//        Query query = getEntityManager()
//                .createQuery("FROM Account e WHERE e.id = :id")
//                .setParameter("id", accountId);
//        return ((Account)query.getFirstResult()).getCustomer().getEmail();
        String MOCK_EMAIL = "wipekxxx@gmail.com";
        return MOCK_EMAIL;
    }

    @Override
    public Account getActualAccount(String accountId) {
        return getEntityManager().find(Account.class, accountId);
    }

    @Override
    public DomesticTransferType getDomesticTransferTypeById(String id) throws NoResultException {
        return getEntityManager().find(DomesticTransferType.class, id);
    }

    @Override
    public TransactionBonus getTransactionBonus(String accountId) throws NoResultException {
        Query query = getEntityManager()
                .createQuery("FROM TransactionBonus e WHERE e.account.id=:id")
                .setParameter("id", accountId);
        TransactionBonus transactionBonus;
        try {
            transactionBonus = (TransactionBonus) query.getSingleResult();
        } catch (NoResultException e) {
            transactionBonus = new TransactionBonus();
        }
        return transactionBonus;
    }

    @Override
    public void updateTransactionBonus(TransactionBonus transactionBonus) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(transactionBonus);
            getEntityManager().flush();
            getEntityManager().clear();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(account);
            getEntityManager().flush();
            getEntityManager().clear();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }
    }


}

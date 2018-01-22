package pl.wipek.payments.ejb.dao.impl;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
}

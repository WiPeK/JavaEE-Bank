package pl.wipek.beneficiaries.ejb.dao.impl;

import pl.wipek.beneficiaries.ejb.dao.BeneficiariesDAO;
import pl.wipek.shared.domain.entity.Beneficiary;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless(name = "BeneficiariesDAOImpl")
public class BeneficiariesDAOImpl extends AbstractDao<String, Beneficiary> implements BeneficiariesDAO {

    @Override
    public Set<Beneficiary> getBeneficiariesFromCustomerDomesticTransfers(String customerId) {
//        Query query = getEntityManager()
//                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.domesticTransfer.account.customer.id=:id")
//                .setParameter("id", customerId);
        Query query = getEntityManager()
                .createQuery("FROM " + entityClass.getCanonicalName() + " e WHERE e.accountNumber LIKE 'PL%'");
        query.setMaxResults(15);
        return new HashSet<>(query.getResultList());
    }

}

package pl.wipek.beneficiaries.services;

import pl.wipek.beneficiaries.ejb.dao.BeneficiariesDAO;
import pl.wipek.shared.domain.entity.DomesticBeneficiary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless
public class BeneficiariesService implements Serializable {

    @EJB(beanInterface = BeneficiariesDAO.class, beanName = "BeneficiariesDAOImpl")
    private BeneficiariesDAO beneficiariesDao;

    public Set<DomesticBeneficiary> getBeneficiariesFromUsersDomesticPayments(String customerId) {
        return beneficiariesDao.getBeneficiariesFromCustomerDomesticTransfers(customerId);
    }


}

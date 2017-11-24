package pl.wipek.beneficiaries.ejb.dao;

import pl.wipek.shared.domain.entity.Beneficiary;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
public interface BeneficiariesDAO extends Dao<String, Beneficiary> {
    public Set<Beneficiary> getBeneficiariesFromUsersDomesticPayments(String userId);
}

package pl.wipek.payments.ejb.dao;

import pl.wipek.shared.domain.entity.DomesticPaymentType;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
public interface PaymentsDAO extends Dao<String, DomesticPaymentType> {
    public Set<DomesticPaymentType> getDomesticPaymentTypes();
}

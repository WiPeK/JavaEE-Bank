package pl.wipek.payments.ejb.dao;

import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.ejb.dao.Dao;

import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
public interface PaymentsDAO extends Dao<String, DomesticTransferType> {
    public Set<DomesticTransferType> getDomesticPaymentTypes();
    public String getCustomerEmailByAccountId(String accountId);
}

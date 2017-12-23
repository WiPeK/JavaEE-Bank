package pl.wipek.payments.ejb.dao.impl;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.domain.entity.DomesticTransferType;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
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

}

package pl.wipek.payments.types.services;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.domain.entity.DomesticPaymentType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless
public class PaymentTypesService implements Serializable {

    @EJB(beanInterface = PaymentsDAO.class, beanName = "PaymentsDaoImpl")
    private PaymentsDAO paymentsDAO;

    public Set<DomesticPaymentType> getDomesticPaymentTypes() {
        return paymentsDAO.getDomesticPaymentTypes();
    }
}

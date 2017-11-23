package pl.wipek.payments.ejb.dao.impl;

import org.mockito.Mock;
import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.domain.entity.DomesticPaymentType;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless(name = "PaymentsDaoImpl")
public class PaymentsDaoImpl extends AbstractDao<String, DomesticPaymentType> implements PaymentsDAO {

    @Mock
    private EntityManager entityManager;

    @Override
    public Set<DomesticPaymentType> getDomesticPaymentTypes() {
        return getMockedDomesticPaymentTypes();
    }

    private Set<DomesticPaymentType> getMockedDomesticPaymentTypes() {
        Set<DomesticPaymentType> domesticPaymentTypes = new HashSet<>();

        DomesticPaymentType type1 = new DomesticPaymentType();
        type1.setId("dsfgdfgdf");
        type1.setValue("Elixir");

        DomesticPaymentType type2 = new DomesticPaymentType();
        type2.setId("dgtgh4tf45r");
        type2.setValue("Blue Cash");

        DomesticPaymentType type3 = new DomesticPaymentType();
        type3.setId("ertregvbfd");
        type3.setValue("Zwykły");

        domesticPaymentTypes.add(type1);
        domesticPaymentTypes.add(type2);
        domesticPaymentTypes.add(type3);

        return domesticPaymentTypes;
    }
}

package pl.wipek.beneficiaries.ejb.dao.impl;

import org.mockito.Mock;
import pl.wipek.beneficiaries.ejb.dao.BeneficiariesDAO;
import pl.wipek.shared.domain.entity.Beneficiary;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Stateless(name = "BeneficiariesDAOImpl")
public class BeneficiariesDAOImpl extends AbstractDao<String, Beneficiary> implements BeneficiariesDAO {

    @Mock
    private EntityManager entityManager;

    @Override
    public Set<Beneficiary> getBeneficiariesFromUsersDomesticPayments(String userId) {
        return getMockedBeneficiaries();
    }

    private Set<Beneficiary> getMockedBeneficiaries() {
        Set<Beneficiary> beneficiaries = new HashSet<>();

        Beneficiary b = new Beneficiary();
        b.setName("Janusz Tracz");
        b.setAccountNumber("PL831050171937392025842606809009");
        b.setAddress("ul. Łopackiego Jacka Augustyna 66\n" +
                "31-831 Kraków");

        Beneficiary b1 = new Beneficiary();
        b1.setName("Marianna Powolna");
        b1.setAccountNumber("PL129253072017537338784286639998");

        Beneficiary b2 = new Beneficiary();
        b2.setName("Gino Lanetti");
        b2.setAccountNumber("PL333353072017537338784286639998");
        b2.setAddress("ul. Gnieźnieńska 10\n" +
                "80-299 Gdańsk");

        Beneficiary b3 = new Beneficiary();
        b3.setName("Adam Adamski");
        b3.setAccountNumber("PL444453072017537338784286639998");

        beneficiaries.add(b);
        beneficiaries.add(b1);
        beneficiaries.add(b2);
        beneficiaries.add(b3);
        return beneficiaries;
    }
}

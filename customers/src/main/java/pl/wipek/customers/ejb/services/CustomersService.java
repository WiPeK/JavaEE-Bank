package pl.wipek.customers.ejb.services;

import pl.wipek.customers.ejb.dao.CustomersDAO;
import pl.wipek.shared.domain.entity.Customer;
import pl.wipek.shared.domain.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 18.09.2017.
 */
@Stateless
public class CustomersService implements Serializable {

    @EJB(beanInterface = CustomersDAO.class, beanName = "JpaCustomersDao")
    private CustomersDAO customersDao;

    public Optional<User> findById(String id) {
        Optional<User> result = null;
        try {
            result = Optional.of(this.customersDao.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Customer> getAll() {
        return this.customersDao.getAll();
    }

}

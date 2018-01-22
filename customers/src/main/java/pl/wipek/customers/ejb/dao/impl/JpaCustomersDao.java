package pl.wipek.customers.ejb.dao.impl;

import pl.wipek.shared.domain.entity.Customer;
import pl.wipek.shared.domain.entity.User;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.customers.ejb.dao.CustomersDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * @author Krzysztof Adamczyk on 19.09.2017.
 */
@Stateless(name = "JpaCustomersDao")
public class JpaCustomersDao extends AbstractDao<String, Customer> implements CustomersDAO {
}

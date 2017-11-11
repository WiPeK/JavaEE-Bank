package pl.wipek.users.ejb.dao.impl;

import pl.wipek.shared.domain.entity.Users;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.users.ejb.dao.UsersDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Krzysztof Adamczyk on 19.09.2017.
 */
@Stateless(name = "JpaUsersDao")
public class JpaUsersDao extends AbstractDao<String, Users> implements UsersDAO {

//    @PersistenceContext(unitName = "pl.wipek.database.users")
//    protected EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

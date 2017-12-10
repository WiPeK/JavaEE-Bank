package pl.wipek.users.ejb.dao.impl;

import pl.wipek.shared.domain.entity.User;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.users.ejb.dao.UsersDAO;

import javax.ejb.Stateless;

/**
 * @author Krzysztof Adamczyk on 19.09.2017.
 */
@Stateless(name = "JpaUsersDao")
public class JpaUsersDao extends AbstractDao<String, User> implements UsersDAO {
}

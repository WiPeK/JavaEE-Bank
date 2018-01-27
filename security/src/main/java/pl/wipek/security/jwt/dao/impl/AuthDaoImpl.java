package pl.wipek.security.jwt.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.security.jwt.dao.AuthDao;
import pl.wipek.shared.domain.entity.User;
import pl.wipek.shared.ejb.dao.impl.AbstractDao;
import pl.wipek.shared.security.SHA512;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Krzysztof Adamczyk on 11.11.2017.
 */
@Stateless(name = "AuthDaoImpl")
public class AuthDaoImpl extends AbstractDao<String, User> implements AuthDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthDaoImpl.class);

    @Override
    public List<User> findByLoginAndPassword(User user) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + entityClass.getName() + " e WHERE e.login='" + user.getLogin() + "' AND e.password='" + user.getPassword() + "'");
        return query.getResultList();
    }

    @Override
    public User isUserExists(User user) {
        String hashedPassword = null;
        try {
            hashedPassword = new SHA512().hash(user.getPassword());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e.toString());
            throw new SecurityException("Invalid user");
        }
        logger.info(hashedPassword);
        user.setPassword(hashedPassword);
        List<User> users = findByLoginAndPassword(user);
        if (users.isEmpty() || users.size() > 1) {
            throw new SecurityException("Invalid user");
        } else {
            user = users.get(0);
            logger.info(user.toString());
        }
        return user;
    }
}

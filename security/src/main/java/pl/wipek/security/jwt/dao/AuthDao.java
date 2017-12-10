package pl.wipek.security.jwt.dao;

import pl.wipek.shared.domain.entity.User;
import pl.wipek.shared.ejb.dao.Dao;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Krzysztof Adamczyk on 11.11.2017.
 */
@Remote
public interface AuthDao extends Dao<String, User> {
    public List<User> findByLoginAndPassword(User user);
    public User isUserExists(User user);
}

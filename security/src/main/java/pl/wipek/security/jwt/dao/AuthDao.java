package pl.wipek.security.jwt.dao;

import pl.wipek.shared.domain.entity.Users;
import pl.wipek.shared.ejb.dao.Dao;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Krzysztof Adamczyk on 11.11.2017.
 */
@Remote
public interface AuthDao extends Dao<String, Users> {
    public List<Users> findByLoginAndPassword(Users user);
    public Users isUserExists(Users user);
}

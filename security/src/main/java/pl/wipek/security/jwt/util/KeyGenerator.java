package pl.wipek.security.jwt.util;

import javax.ejb.Remote;
import java.security.Key;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@Remote
public interface KeyGenerator {

    Key generateKey();
}

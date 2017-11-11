package pl.wipek.security.jwt.util;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import java.security.Key;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@Stateless
public class SimpleKeyGenerator implements KeyGenerator {
    @Override
    public Key generateKey() {
        String keyString = "kamehameha";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}

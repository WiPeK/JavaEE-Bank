package pl.wipek.security.jwt.util;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
public class PasswordUtils {

    public static String digestPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return new String(Base64.getEncoder().encode(passwordDigest));
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }
}

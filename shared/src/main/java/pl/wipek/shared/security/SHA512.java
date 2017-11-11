package pl.wipek.shared.security;

import com.google.common.io.BaseEncoding;
import com.google.common.primitives.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author Krzysztof Adamczyk on 08.11.2017.
 */
public class SHA512 {
    private static final Logger logger = LoggerFactory.getLogger(SHA512.class);
    private static final String ALGORITHM = "SHA-512";
    private static final int ITERATIONS = 1000000;
    private static final int SALT_SIZE = 64;

    public String hash(String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
       // byte[] salt = generateSalt();
        byte[] hash = calculateHash(password);
        BigInteger intNumber = new BigInteger(1, hash);
        String strHashCode = intNumber.toString(16);

        while (strHashCode.length() < 128) {
            strHashCode = "0" + strHashCode;
        }
        return strHashCode;
    }

//    private byte[] generateSalt() {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[SALT_SIZE];
//        random.nextBytes(salt);
//
//        return salt;
//    }

    private byte[] calculateHash(String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
//        md.reset();
        md.update(Bytes.concat(password.getBytes("UTF-8")));
        byte[] hash = md.digest();

        for (int i = 0; i < ITERATIONS; i++) {
//            md.reset();
            hash = md.digest(hash);
        }

        return hash;
    }

//    private boolean verifyPassword(byte[] originalHash, String password, byte[] salt) throws
//            NoSuchAlgorithmException, UnsupportedEncodingException {
//        byte[] comparisonHash = calculateHash(password, salt);
//
//        logger.info("hash 1: {}", BaseEncoding.base16().encode(originalHash));
//        logger.info("hash 2: {}", BaseEncoding.base16().encode(comparisonHash));
//
//        return comparePasswords(originalHash, comparisonHash);
//    }

//    /**
//     * Compares the two byte arrays in length-constant time using XOR.
//     *
//     * @param originalHash   The original password hash
//     * @param comparisonHash The comparison password hash
//     * @return True if both match, false otherwise
//     */
//    public boolean comparePasswords(byte[] originalHash, byte[] comparisonHash) {
//        int diff = originalHash.length ^ comparisonHash.length;
//        for (int i = 0; i < originalHash.length && i < comparisonHash.length; i++) {
//            diff |= originalHash[i] ^ comparisonHash[i];
//        }
//
//        return diff == 0;
//    }
}

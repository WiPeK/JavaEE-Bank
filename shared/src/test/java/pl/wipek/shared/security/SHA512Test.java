package pl.wipek.shared.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Krzysztof Adamczyk on 10.11.2017.
 */
public class SHA512Test {

    private SHA512 sha512;

    @Before
    public void setUp() {
        sha512 = new SHA512();
    }

    @Test
    public void hash() throws Exception {
        String firstHash = sha512.hash("12345678");
        String secondHash = sha512.hash("12345678");
        String thirdHash = sha512.hash("testtesttest");

        System.out.println(firstHash);

        Assert.assertEquals(firstHash, secondHash);
        Assert.assertNotEquals(firstHash, thirdHash);
        Assert.assertNotEquals(secondHash, thirdHash);
    }

}
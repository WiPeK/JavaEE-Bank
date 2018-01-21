package pl.wipek.payments.tokens;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenGeneratorTest {

    @Test
    public void generateToken() {
        String token = TokenGenerator.generateToken();
        Assert.assertEquals(token.length(), 8);
    }
}
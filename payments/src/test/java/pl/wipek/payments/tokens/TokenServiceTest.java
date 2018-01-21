package pl.wipek.payments.tokens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.payments.ejb.dao.impl.PaymentsDaoImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class TokenServiceTest {

    private TokenService tokenService;
    private PaymentsDAO paymentsDAO;

    @Before
    public void setUp() throws Exception {
        tokenService = new TokenService();
        paymentsDAO = new PaymentsDaoImpl();

        Field field = TokenService.class.getDeclaredField("paymentsDAO");
        field.setAccessible(true);
        field.set(tokenService, paymentsDAO);
    }

    @Test
    public void generateToken() {
        try {
            String token = tokenService.generateToken("test");
            System.out.println(token);
            Assert.assertEquals(token.length(), 8);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
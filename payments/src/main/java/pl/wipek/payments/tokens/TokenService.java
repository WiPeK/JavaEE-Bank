package pl.wipek.payments.tokens;

import pl.wipek.payments.ejb.dao.PaymentsDAO;
import pl.wipek.shared.emails.EmailSender;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TokenService {

    @EJB(beanInterface = PaymentsDAO.class, beanName = "PaymentsDaoImpl")
    private PaymentsDAO paymentsDAO;

    public String generateToken(String accountId) throws IOException, MessagingException {
        String token = TokenGenerator.generateToken();
        String email = paymentsDAO.getCustomerEmailByAccountId(accountId);
        List<String> mockedEmails = new ArrayList<>(4);
        mockedEmails.add("wipekxxx@gmail.com");
//        mockedEmails.add("skladanowski1a@gmail.com");
//        mockedEmails.add("marelix2@gmail.com");
//        mockedEmails.add("pyk.bartlomiej@gmail.com");
        EmailSender.sendEmailToMany("TOKEN", token, mockedEmails);
        return token;
    }
}

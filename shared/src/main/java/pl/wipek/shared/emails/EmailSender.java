package pl.wipek.shared.emails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(@NotNull String subject, @NotNull String message, @NotNull String mailTo) throws IOException, MessagingException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("email.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mailFrom"), properties.getProperty("password"));
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(properties.getProperty("mailFrom")));
        InternetAddress[] toAddresses = { new InternetAddress(mailTo) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html");

        Transport.send(msg);
    }
}

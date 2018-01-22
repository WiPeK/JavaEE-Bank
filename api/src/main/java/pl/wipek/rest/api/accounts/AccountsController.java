package pl.wipek.rest.api.accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.ejb.services.AccountsService;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@ApplicationScoped
@Path("accounts")
public class AccountsController extends Application {
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @EJB
    private AccountsService accountsService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account account) throws JAXBException {
        Account createdAccount = accountsService.createNewAccount(account);
        String resultJson = JsonSerializer.convertObject(createdAccount, Account.class);
        return Response.ok(resultJson).build();
    }
}

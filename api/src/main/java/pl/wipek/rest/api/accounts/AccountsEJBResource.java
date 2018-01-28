package pl.wipek.rest.api.accounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.ejb.services.AccountsService;
import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Path("/accounts")
public class AccountsEJBResource extends Application {

    private static final Logger logger = LoggerFactory.getLogger(AccountsEJBResource.class);

    @EJB
    private AccountsService accountsService;

    @GET
    @Path("customers/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAccounts(@PathParam("customerId") String customerId) throws JAXBException, JsonProcessingException {
        Set<Account> accounts = accountsService.getUserAccounts(customerId);
//        String resultJson = JsonSerializer.convertSet(accounts, Account.class);
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(accounts);
        return Response.ok(resultJson).build();
    }
}

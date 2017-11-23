package pl.wipek.rest.api.accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.ejb.services.AccountsService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Krzysztof Adamczyk on 22.11.2017.
 */
@Path("/accounts")
public class AccountsEJBResource extends Application {

    private static final Logger logger = LoggerFactory.getLogger(AccountsEJBResource.class);

    @EJB
    private AccountsService accountsService;

    @GET
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAccounts(@PathParam("userId") String userId) {
        logger.info(userId);
        return Response.ok(accountsService.getUserAccounts(userId)).build();
    }
}

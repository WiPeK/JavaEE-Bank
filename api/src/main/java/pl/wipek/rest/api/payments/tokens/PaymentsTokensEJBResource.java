package pl.wipek.rest.api.payments.tokens;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.wipek.payments.tokens.TokenService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/payments")
@ApplicationScoped
public class PaymentsTokensEJBResource extends Application {

    @EJB
    private TokenService tokenService;

    @GET
    @Path("/token/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTokenForPayment(@PathParam("accountId") String accountId) throws IOException, MessagingException {
//        String token = tokenService.generateToken(accountId);
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(tokenService.generateToken(accountId));
        System.out.println(resultJson);
        return Response.ok(resultJson).build();
    }
}

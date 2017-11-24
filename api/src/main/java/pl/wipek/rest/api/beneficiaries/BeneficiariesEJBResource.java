package pl.wipek.rest.api.beneficiaries;

import pl.wipek.beneficiaries.services.BeneficiariesService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Path("/beneficiaries")
@ApplicationScoped
public class BeneficiariesEJBResource extends Application {

    @EJB
    private BeneficiariesService beneficiariesService;

    @GET
    @Path("/domestic/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBeneficiariesFromUsersDomesticPayments(@PathParam("userId") String userId) {
        return Response.ok(beneficiariesService.getBeneficiariesFromUsersDomesticPayments(userId)).build();
    }
}
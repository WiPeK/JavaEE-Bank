package pl.wipek.rest.api.payments.types;

import pl.wipek.payments.types.services.PaymentTypesService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Path("/payments")
@ApplicationScoped
public class PaymentsTypesEJBResource extends Application {

    @EJB
    private PaymentTypesService paymentTypesService;

    @GET
    @Path("/types/domestic")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomesticPaymentTypes() {
        return Response.ok(paymentTypesService.getDomesticPaymentTypes()).build();
    }

}

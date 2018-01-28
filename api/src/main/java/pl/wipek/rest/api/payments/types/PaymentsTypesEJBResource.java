package pl.wipek.rest.api.payments.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = null;
        try {
            resultJson = mapper.writeValueAsString(paymentTypesService.getDomesticPaymentTypes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(resultJson).build();
    }

}

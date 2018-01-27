package pl.wipek.rest.api.beneficiaries;

import pl.wipek.beneficiaries.services.BeneficiariesService;
import pl.wipek.shared.domain.entity.DomesticBeneficiary;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
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
 * @author Krzysztof Adamczyk on 23.11.2017.
 */
@Path("/beneficiaries")
@ApplicationScoped
public class BeneficiariesEJBResource extends Application {

    @EJB
    private BeneficiariesService beneficiariesService;

    @GET
    @Path("/domestic/customers/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBeneficiariesFromUsersDomesticPayments(@PathParam("customerId") String customerId) throws JAXBException {
        Set<DomesticBeneficiary> domesticBeneficiaries = beneficiariesService.getBeneficiariesFromUsersDomesticPayments(customerId);
        String resultJson = JsonSerializer.convertSet(domesticBeneficiaries, DomesticBeneficiary.class);
        return Response.ok(resultJson).build();
    }
}


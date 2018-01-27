package pl.wipek.rest.api.payments.domestic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.payments.transfers.TransfersService;
import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.payments.transfers.response.DomesticTransferResponse;
import pl.wipek.payments.types.services.TransferTypes;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
@ApplicationScoped
public class DomesticTransferController extends Application {

    private static final Logger logger = LoggerFactory.getLogger(DomesticTransferController.class);

    @EJB
    private TransfersService transfersService;

    @POST
    @Path("/domestic/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tryExecuteTransfer(@PathParam("userId") String userId, DomesticTransferRequest domesticTransferRequest) throws Exception {
        logger.info(domesticTransferRequest.toString());
        DomesticTransferResponse domesticTransferResponse = (DomesticTransferResponse) transfersService.tryExecuteTransfer(userId, domesticTransferRequest, TransferTypes.DOMESTIC);
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = mapper.writeValueAsString(domesticTransferResponse);
        //logger.info(resultJson);
        System.out.println(domesticTransferResponse);
        return Response.ok(resultJson).build();
    }
}

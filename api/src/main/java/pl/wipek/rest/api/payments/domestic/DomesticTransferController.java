package pl.wipek.rest.api.payments.domestic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.payments.transfers.TransfersService;
import pl.wipek.payments.transfers.requests.DomesticTransferRequest;
import pl.wipek.payments.transfers.response.TransferResponse;
import pl.wipek.payments.types.services.TransferTypes;
import pl.wipek.shared.util.converter.JsonDeserializer;

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
    public Response tryExecuteTransfer(@PathParam("userId") String userId, DomesticTransferRequest domesticTransferRequest) {
        logger.info(domesticTransferRequest.toString());
        TransferResponse domesticTransferResponse = transfersService.tryExecuteTransfer(userId, domesticTransferRequest, TransferTypes.DOMESTIC);
        return Response.ok(domesticTransferResponse).build();
    }
}

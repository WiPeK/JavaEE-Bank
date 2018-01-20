package pl.wipek.rest.api.test_transfer_command;


import ejb.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;
import pl.wipek.shared.util.converter.JsonSerializer;
import transfer.interfaces.Transfer;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Set;



/**
 * Created by Michał on 20.01.2018.
 */

@Path("/transfer_test")
public class TransferEJBResource extends Application {
    private static final Logger logger = LoggerFactory.getLogger(TransferEJBResource.class);

    @EJB
    private TransferService transferService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAccounts(String customerId) throws JAXBException {
        Set<ScheduledTransferShared> transfers = transferService.getTransfers(customerId);
        String resultJson = JsonSerializer.convertSet(transfers, ScheduledTransferShared.class);
        return Response.ok(resultJson).build();
    }
}



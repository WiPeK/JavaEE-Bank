package pl.wipek.rest.api.test_transfer_command;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern.ScheduledTransferShared;
import pl.wipek.shared.util.converter.JsonSerializer;

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

@Path("/payments")
public class TransferEJBResource extends Application {
    private static final Logger logger = LoggerFactory.getLogger(TransferEJBResource.class);

    @EJB
    private ejb.services.TransferService transferService;


    @GET
    @Path("/scheduled-transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransfers() throws JAXBException {
        Set<ScheduledTransferShared> transfers = transferService.getTransfers();
//        String resultJson = JsonSerializer.convertSet(transfers, ScheduledTransferShared.class);
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = null;
        try {
            resultJson = mapper.writeValueAsString(transfers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(resultJson).build();
    }
}



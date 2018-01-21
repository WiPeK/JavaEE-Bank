package pl.wipek.rest.api.deposits;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.deposits.ejb.services.*;
import pl.wipek.shared.domain.entity.Deposit;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@Path("/deposits")
public class DepositsEJBResource extends Application{

    private static final Logger logger = LoggerFactory.getLogger(DepositsEJBResource.class);

    @EJB
    private DepositsService depositsService;

    @GET
    @Path("deposits/{depositsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeposits(@PathParam("depositsId")String depositsId) throws JAXBException, NotFoundException{
        Deposit deposits = depositsService.getDeposits(depositsId);
        String resultJson = JsonSerializer.convertObject(deposits,Deposit.class);
        return Response.ok(resultJson).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tryAddNewDeposit(Deposit deposit) throws DaoException, JAXBException{
        depositsService.saveDeposit(deposit);
        String resultJson = JsonSerializer.convertObject(deposit,Deposit.class);
        return Response.ok(resultJson).build();
    }
}

package pl.wipek.rest.api.loans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.loans.ejb.services.LoansService;
import pl.wipek.shared.domain.entity.Loan;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.util.converter.JsonSerializer;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Set;

@Path("/loans")
public class LoansEJBResource extends Application {

    private static final Logger logger = LoggerFactory.getLogger(LoansEJBResource.class);

    @EJB
    public LoansService loansService;

    @GET
    @Path("loans/{loansId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoans(@PathParam("loansId")String loansId) throws JAXBException, NotFoundException {
        Loan loans = loansService.getLoans(loansId);
        String resultJson = JsonSerializer.convertObject(loans,Loan.class);
        return Response.ok(resultJson).build();
    }

    @GET
    @Path("loans/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLoans() throws JAXBException, NotFoundException, JsonProcessingException {
        Set<Loan> loans = loansService.getAllLoans();
        ObjectMapper mapper = new ObjectMapper();
//        String resultJson = JsonSerializer.convertSet(loans,Loan.class);
        String resultJson = mapper.writeValueAsString(loans);
        return Response.ok(resultJson).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tryAddNewLoan(Loan loan) throws DaoException, JAXBException{
        loansService.saveLoan(loan);
        String resultJson = JsonSerializer.convertObject(loan,Loan.class);
        return Response.ok(resultJson).build();
    }
}

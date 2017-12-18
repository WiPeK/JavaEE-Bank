package pl.wipek.rest.api.customers;

import pl.wipek.customers.ejb.services.CustomersService;
import pl.wipek.shared.domain.entity.Customer;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.util.Set;

/**
 * @author Krzysztof Adamczyk on 20.09.2017.
 */
@Path("/customers")
@ApplicationScoped
public class CustomersEJBResource extends Application {

    @EJB
    private CustomersService customersService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Interceptors(RequestInterceptor.class)
    public Response getAll(@Context Request request) throws JAXBException {
        Set<Customer> result = customersService.getAll();

        String resultJson = JsonSerializer.convertSet(result, Customer.class);

        return Response.ok(resultJson.toString()).build();
    }
}

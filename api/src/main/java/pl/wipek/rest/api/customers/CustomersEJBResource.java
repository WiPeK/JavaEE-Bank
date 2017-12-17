package pl.wipek.rest.api.customers;

import pl.wipek.request.RequestInterceptor;
import pl.wipek.customers.ejb.services.CustomersService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

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
    @Interceptors(RequestInterceptor.class)
    public Response getAll(@Context Request request) {
        return Response.ok(customersService.getAll()).build();
    }
}

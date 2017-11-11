package pl.wipek.rest.api.users;

import pl.wipek.request.RequestInterceptor;
import pl.wipek.users.ejb.services.UsersService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

/**
 * @author Krzysztof Adamczyk on 20.09.2017.
 */
@Path("/users")
@ApplicationScoped
public class UsersEJBResource extends Application {

    @EJB
    private UsersService usersService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Interceptors(RequestInterceptor.class)
    public Response getAll(@Context Request request) {
        return Response.ok(usersService.getAll()).build();
    }
}

package pl.wipek.rest.api.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.security.jwt.annotations.JWTTokenNeeded;
import pl.wipek.security.jwt.service.AuthService;
import pl.wipek.shared.domain.entity.Users;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@Path("/auth")
@ApplicationScoped
@XmlRootElement
public class AuthEJBResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthEJBResource.class);

    @EJB
    private AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response auth(Users user) {
        logger.info(user.toString());
        try {
            user = authService.autenticate(user);
            String token = authService.issueToken(user);
            if (token == null) {
                throw new Exception("Error in token generating");
            }
            return Response.ok(user).header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            logger.error(e.toString());
            return Response.status(UNAUTHORIZED).build();
        }
    }


    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response logout() {
        return Response.ok().build();
    }

}

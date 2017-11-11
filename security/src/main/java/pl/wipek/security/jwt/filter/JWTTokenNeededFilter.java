package pl.wipek.security.jwt.filter;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.security.jwt.annotations.JWTTokenNeeded;
import pl.wipek.security.jwt.util.KeyGenerator;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTTokenNeededFilter.class);

    @EJB(beanInterface = KeyGenerator.class, beanName = "SimpleKeyGenerator")
    private KeyGenerator keyGenerator;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Validate the token
            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            logger.info("#### valid token : " + token);

        } catch (Exception e) {
            logger.warn("#### invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}

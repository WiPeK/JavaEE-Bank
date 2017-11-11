package pl.wipek.security.jwt.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface JWTTokenNeeded {
}

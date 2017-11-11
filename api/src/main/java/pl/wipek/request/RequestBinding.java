package pl.wipek.request;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Krzysztof Adamczyk on 03.10.2017.
 */
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface RequestBinding {
}

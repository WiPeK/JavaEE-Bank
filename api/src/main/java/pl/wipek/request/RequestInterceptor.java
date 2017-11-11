package pl.wipek.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Krzysztof Adamczyk on 02.10.2017.
 */
@Interceptor
@RequestBinding
public class RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @AroundInvoke
    private Object aroundInvoke(InvocationContext context) throws Exception {
        Object result = null;
        try {
            result = context.proceed();
            logger.info(result.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return result;
    }
}

package pl.wipek.rest.api.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Krzysztof Adamczyk on 11.11.2017.
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.disableMoxyJson", true);
        return properties;
    }
}

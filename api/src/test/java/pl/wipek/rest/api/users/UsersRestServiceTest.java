package pl.wipek.rest.api.users;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.shared.domain.entity.Users;
import pl.wipek.users.ejb.services.UsersService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Krzysztof Adamczyk on 09.10.2017.
 */
public class UsersRestServiceTest {

    @Mock
    private UsersService usersService;

    private UsersEJBResource usersRestService = new UsersEJBResource();

    private static final Logger logger = LoggerFactory.getLogger(UsersRestServiceTest.class);

    @Before
    public void setUp() throws Exception {
        Class<?> usersRestServiceClass = usersRestService.getClass();

        Field field = usersRestServiceClass.getDeclaredField("usersService");
        field.setAccessible(true);
        field.set(usersRestService, usersService);
        field.setAccessible(false);
    }

    @Test
    public void getAll() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/api");
        WebTarget targetUpdated = target.path("/users");
        Response response = target.request("application/json").get();

        when(usersService.getAll()).thenReturn(initializeUsersList());

        assertEquals(Response.ok(initializeUsersList()).build(), response);
    }

    private List<Users> initializeUsersList() {
        List<Users> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Users user = new Users();
            user.setIdUsers("UserId" + i);
            user.setLogin("UserLogin" + i);
            result.add(user);
        }

        return result;
    }

}
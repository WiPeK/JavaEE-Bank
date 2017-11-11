package pl.wipek.users.ejb.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.wipek.users.ejb.dao.UsersDAO;
import pl.wipek.users.ejb.dao.impl.JpaUsersDao;
import pl.wipek.users.entity.Users;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.mockito.Matchers.any;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * @author Krzysztof Adamczyk on 09.10.2017.
 */
public class UsersServiceTest {

    @Mock
    private UsersDAO usersDAO;

    private UsersService usersService = new UsersService();

    @Before
    public void setUp() throws Exception {

        usersDAO = mock(JpaUsersDao.class);

        Class<?> usersServiceClass = usersService.getClass();

        Field field = usersServiceClass.getDeclaredField("usersDao");
        field.setAccessible(true);
        field.set(usersService, usersDAO);
        field.setAccessible(false);
    }

    @Test
    public void findById() throws Exception {
        Users user = new Users();
        user.setIdUsers("QAZXCE");
        user.setLogin("XYZ");

        Optional<Users> userToReturn = Optional.of(user);
        Mockito.when(usersDAO.findById(any(String.class))).thenReturn(user);

        Optional<Users> returned = usersService.findById(user.getIdUsers());
        assertEquals(userToReturn, returned);
    }
}
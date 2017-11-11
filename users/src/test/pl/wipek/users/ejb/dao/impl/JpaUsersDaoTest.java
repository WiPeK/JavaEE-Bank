package pl.wipek.users.ejb.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.wipek.users.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Krzysztof Adamczyk on 06.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class JpaUsersDaoTest {

    private JpaUsersDao jpaUsersDao = new JpaUsersDao();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EntityTransaction transaction;

    @Before
    public void setUp() throws Exception {
        EntityManager entityManager = mock(EntityManager.class);

        Class<?> usersDaoClass = jpaUsersDao.getClass();

        Field field = usersDaoClass.getDeclaredField("entityManager");
        field.setAccessible(true);
        field.set(jpaUsersDao, entityManager);
        field.setAccessible(false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getEntityManager() throws Exception {
        assertNotNull(jpaUsersDao.getEntityManager());
    }

    @Test
    public void persist() throws Exception {
        Users user = new Users();
        user.setLogin("LOGIN");
        user.setIdUsers("ID");

        List<Users> usersList = initializeUsersList();

        when(jpaUsersDao.getEntityManager().getTransaction()).thenReturn(transaction);

        doAnswer((invocation) -> {
            usersList.add(user);
            return null;
        }).when(jpaUsersDao.getEntityManager()).persist(any());


        Users returned = jpaUsersDao.persist(user);

        assertSame(user, returned);
        assertThat(initializeUsersList(), is(not(usersList)));
        assertEquals(user, usersList.get(usersList.size() - 1));
        verify(jpaUsersDao.getEntityManager()).persist(user);
    }

    @Test
    public void persistException() {
        Users user = new Users();
        user.setLogin("LOGIN");
        user.setIdUsers("ID");

        IllegalStateException illegalStateException = new IllegalStateException("");
        when(jpaUsersDao.getEntityManager().getTransaction()).thenReturn(transaction);
        doThrow(illegalStateException).when(jpaUsersDao.getEntityManager()).persist(user);

        try {
            jpaUsersDao.persist(user);
        } catch (Exception e) {
            assertSame(illegalStateException, e.getCause());
        }
        verify(jpaUsersDao.getEntityManager()).persist(user);
    }

    @Test
    public void merge() throws Exception {
        Users user = initializeUsersList().get(0);
        user.setLogin("XXXXXXXXXXXXXXX");

        List<Users> users = initializeUsersList();
        when(jpaUsersDao.getEntityManager().getTransaction()).thenReturn(transaction);

        when(jpaUsersDao.getEntityManager().merge(any())).thenAnswer((invocation) -> {
            updateList(users, user);
            return null;
        }).thenReturn(user);

        jpaUsersDao.merge(user);

        assertThat(initializeUsersList(), is(not(users)));
        assertEquals(users.get(0), user);
        verify(jpaUsersDao.getEntityManager()).merge(user);
    }

    private void updateList(List<Users> users, Users user) {
        users.get(0).setIdUsers(user.getIdUsers());
        users.get(0).setLogin(user.getLogin());
    }

    @Test
    public void remove() throws Exception {
        List<Users> usersList = initializeUsersList();

        when(jpaUsersDao.getEntityManager().find(any(), any())).thenReturn(usersList.get(0));
        when(jpaUsersDao.getEntityManager().getTransaction()).thenReturn(transaction);

        doAnswer((invocation) -> {
            removeUserFromList(usersList);
            return null;
        }).when(jpaUsersDao.getEntityManager()).remove(any());

        assertThat(initializeUsersList(), is(not(jpaUsersDao.remove(usersList.get(0).getIdUsers()))));
    }

    private void removeUserFromList(List<Users> users) {
        users.remove(0);
    }

//    @Test
//    public void removeNotFound() throws Exception {
//        when(jpaUsersDao.getEntityManager().find(any(), any())).thenReturn(null);
//
//        thrown.expect(NotFoundException.class);
//        thrown.expectMessage(startsWith("Can not remove object which doesn't exists"));
//        jpaUsersDao.remove("qaz");
//    }


    @Test
    public void findById() throws Exception {
        String id = "QAZXSW!@#";
        Users userToReturn = new Users();
        userToReturn.setIdUsers(id);
        userToReturn.setLogin("Test");

        when(jpaUsersDao.getEntityManager().find(any(), any())).thenReturn(userToReturn);

        Users returnedUser = jpaUsersDao.findById(id);

        assertEquals(userToReturn, returnedUser);
    }

    @Test
    public void getAll() throws Exception {
        List<Users> usersListToReturn = initializeUsersList();

        String queryString = "SELECT e FROM " + Users.class.getName() + " e";

        Query query = mock(Query.class);

        when(query.getResultList()).thenReturn(usersListToReturn);
        when(jpaUsersDao.getEntityManager().createQuery(queryString)).thenReturn(query);

        List<Users> returnedUsersList = jpaUsersDao.getAll();
        assertEquals(usersListToReturn, returnedUsersList);
        verify(query).getResultList();
        verify(jpaUsersDao.getEntityManager()).createQuery(queryString);
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
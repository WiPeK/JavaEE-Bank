package pl.wipek.accounts.ejb.dao.impl;

import org.junit.*;
import org.junit.runner.RunWith;
import pl.wipek.accounts.ejb.dao.AccountsDAO;
import pl.wipek.shared.domain.entity.Account;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Ignore
public class AccountsDaoImplTest {

    protected static EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;


    @BeforeClass
    public static void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pl.wipek.database");
    }

    @Before
    public void beginTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void getUserAccounts() {
        Query query = entityManager
                .createQuery("FROM Customer e WHERE e.id=:id")
                .setParameter("id", "6075205366900CAAE050EDD4221D1C44");
//        Set<Account> result = accountsDAO.getUserAccounts("5a2c48b072938305fc481165");
        Set<Account> result = new HashSet<>(query.getResultList());
        System.out.println(result);
        Assert.assertNotEquals(0, result.size());
    }
}
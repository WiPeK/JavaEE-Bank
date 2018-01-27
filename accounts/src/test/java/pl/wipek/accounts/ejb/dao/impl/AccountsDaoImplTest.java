package pl.wipek.accounts.ejb.dao.impl;

import org.junit.*;
import pl.wipek.shared.domain.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

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
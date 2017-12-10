package pl.wipek.users.ejb.dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import pl.wipek.shared.domain.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Krzysztof Adamczyk on 30.11.2017.
 */
public class JpaUsersDaoDbTest {
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
    @Ignore
    public void getAll() {
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Users.class).get();
//
//        Query query = queryBuilder.keyword().onFields("id", "login").matching("wipekwipek").createQuery();
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Users.class);
//
//        fullTextQuery.initializeObjectsWith(ObjectLookupMethod.SKIP, DatabaseRetrievalMethod.FIND_BY_ID);
//
//        List<Users> res = fullTextQuery.getResultList();
//        System.out.println(res);
//
//        assertEquals(res.size(), 1);
//
//        entityManager.close();
    }

    @Test
    public void getAllGit() {
        @SuppressWarnings("unchecked")
        List<User> res = entityManager.createNativeQuery("{ $query :{}}", User.class).getResultList();
        System.out.println(res);

        assertEquals(res.size(), 2);
    }

    @Test
    public void getAllWithJPQLQuery() {
        List<User> res = entityManager.createQuery("SELECT U FROM Users U", User.class).getResultList();
        System.out.println(res);

        assertEquals(res.size(), 2);
    }

}
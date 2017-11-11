package pl.wipek.users.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Krzysztof Adamczyk on 20.08.2017.
 */
@Deprecated
public class EntityManagerResource {

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.wipek.database.users");
        return emf.createEntityManager();
    }
}

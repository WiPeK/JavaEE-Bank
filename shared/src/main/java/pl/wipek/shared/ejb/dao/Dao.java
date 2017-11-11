package pl.wipek.shared.ejb.dao;

import javassist.NotFoundException;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;

import javax.ejb.Remote;
import javax.ejb.RemoteHome;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Krzysztof Adamczyk on 21.09.2017.
 */
@Remote
public interface Dao<K, E> extends Serializable {

    /**
     * Saves an E entity to database
     * @param entity
     * @return newly created entity
     * @throws DaoException
     */
    public E persist(E entity) throws DaoException;

    /**
     * Marges objects with the same identifier within a session into a newly
     * created object.
     *
     * @param entity
     * @return a newly created instance merged.
     * @throws DaoException
     */
    public E merge(E entity) throws DaoException;

    /**
     * Deletes the entity.
     *
     * @param id of entity
     * @throws NotFoundException if the user does not exist in database.
     * @throws DaoException
     */
    public boolean remove(K id) throws NotFoundException, DaoException;

    /**
     * Deletes the entity.
     *
     * @param id entity
     * @throws NotFoundException if the entity does not exist in database.
     */
    public E findById(K id) throws NotFoundException;

    /**
     * Finds all objects of an entity class.
     *
     * @return List with objects
     */
    public List<E> getAll();
}
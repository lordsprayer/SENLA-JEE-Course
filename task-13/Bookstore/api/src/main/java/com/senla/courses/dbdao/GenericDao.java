package com.senla.courses.dbdao;

import com.senla.courses.Identified;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Creates a new record corresponding to the object 'object'
     */
    void persist(T object, EntityManager entityManager);

    /**
     * Returns an object corresponding to a record with the primary key 'key' or null
     */
    T getByPK(PK key, EntityManager entityManager);

    /**
     * Saves the state of the object in the database
     */
    void update(T object, EntityManager entityManager);

    /**
     * Deletes an object record from the database
     */
    void delete(T object, EntityManager entityManager);

    /**
     * Returns a list of objects corresponding to all records in the database
     */
    List<T> getAll(EntityManager entityManager);
}


package com.senla.courses.dbdao;

import com.senla.courses.Identified;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface GenericDao <T extends Identified<PK>, PK extends Serializable> {

    /** Creates a new record corresponding to the object 'object' */
    T persist(T object, Connection connection);

    /** Returns an object corresponding to a record with the primary key 'key' or null */
    T getByPK(PK key, Connection connection);

    /** Saves the state of the object in the database */
    void update(T object, Connection connection);

    /** Deletes an object record from the database */
    void delete(T object, Connection connection);

    /** Returns a list of objects corresponding to all records in the database*/
    List<T> getAll(Connection connection);
}

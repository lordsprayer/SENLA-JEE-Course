package com.senla.courses.api.dbdao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface GenericDao <T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись, соответствующую объекту object */
    T persist(T object, Connection connection);

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    T getByPK(PK key, Connection connection);

    /** Сохраняет состояние объекта в базе данных */
    void update(T object, Connection connection);

    /** Удаляет запись об объекте из базы данных */
    void delete(T object, Connection connection);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<T> getAll(Connection connection);
}

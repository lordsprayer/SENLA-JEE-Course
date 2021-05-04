package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.HibernateGenericDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Singleton
public abstract class HibernateAbstractDao <T extends Identified<PK>, PK extends Serializable> implements HibernateGenericDao <T, PK> {

    private static final Logger log = LogManager.getLogger(HibernateAbstractDao.class);

    @Override
    public void persist(T object, EntityManager entityManager) {
        try {
            entityManager.persist(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when saving an object " + object.toString());
            throw new DaoException("Error when saving an object", e);
        }
    }

    @Override
    public T getByPK(PK key, EntityManager entityManager) {
        try {
            return entityManager.find(getClazz(), key);
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    public void update(T object, EntityManager entityManager) {
        try {
            entityManager.merge(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when updating an object " + object.toString());
            throw new DaoException("Error when updating an object", e);
        }
    }

    @Override
    public void delete(T object, EntityManager entityManager) {
        try {
            entityManager.remove(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when deleting an object " + object.toString());
            throw new DaoException("Error when deleting an object", e);
        }
    }

    @Override
    public List<T> getAll(EntityManager entityManager) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(getClazz());
            Root<T> rootEntry = cq.from(getClazz());
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    protected abstract Class<T> getClazz();
}

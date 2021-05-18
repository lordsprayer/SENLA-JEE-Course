package com.senla.courses.dao;

import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Identified;
import com.senla.courses.util.ConstantUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractDao<T extends Identified<PK>, PK extends Serializable> extends ConstantUtil implements GenericDao<T, PK> {

    private static final Logger log = LogManager.getLogger(AbstractDao.class);
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void persist(T object) {
        try {
            entityManager.persist(object);
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR + object.toString());
            throw new DaoException(SAVING_ERROR, e);
        }
    }

    @Override
    public T getByPK(PK key) {
        try {
            return entityManager.find(getClazz(), key);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void update(T object) {
        try {
            entityManager.merge(object);
        } catch (Exception e) {
            log.log(Level.WARN, UPDATING_ERROR + object.toString());
            throw new DaoException(UPDATING_ERROR, e);
        }
    }

    @Override
    public void delete(T object) {
        try {
            entityManager.remove(object);
        } catch (Exception e) {
            log.log(Level.WARN, DELETING_ERROR + object.toString());
            throw new DaoException(DELETING_ERROR, e);
        }
    }

    @Override
    public List<T> getAll() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(getClazz());
            Root<T> rootEntry = cq.from(getClazz());
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    protected abstract Class<T> getClazz();
}

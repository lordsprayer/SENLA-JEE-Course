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
public class HibernateAbstractDao <T extends Identified<PK>, PK extends Serializable> implements HibernateGenericDao <T, PK> {

    private static final Logger log = LogManager.getLogger(HibernateAbstractDao.class);
    private final EntityManager em;
    private Class<T> type;

    public HibernateAbstractDao () {
        this.em = HibernateUtil.getEntityManager();
    }

    @Override
    public void persist(T object) {
        try {
            em.persist(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when saving an object " + object.toString());
            throw new DaoException("Error when saving an object", e);
        }
    }

    @Override
    public T getByPK(PK key) {
        try {
            return em.find(type, key);
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    public void update(T object) {
        try {
            em.merge(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when updating an object " + object.toString());
            throw new DaoException("Error when updating an object", e);
        }
    }

    @Override
    public void delete(T object) {
        try {
            em.remove(object);
        } catch (Exception e) {
            log.log(Level.WARN, "Error when deleting an object " + object.toString());
            throw new DaoException("Error when deleting an object", e);
        }
    }

    @Override
    public List<T> getAll() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);
            Root<T> rootEntry = cq.from(type);
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = em.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }
}

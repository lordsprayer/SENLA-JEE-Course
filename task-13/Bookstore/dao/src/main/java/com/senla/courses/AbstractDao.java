package com.senla.courses;

import com.senla.courses.dbdao.GenericDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDao<T extends Identified<PK>, PK extends Serializable> implements GenericDao<T, PK> {

    private static final Logger log = LogManager.getLogger(AbstractDao.class);
    protected static final String SAVING_ERROR = "Error when saving an object ";
    protected static final String SEARCH_ERROR = "Search showed no matches ";
    protected static final String UPDATING_ERROR = "Error when updating an object ";
    protected static final String DELETING_ERROR = "Error when deleting an object ";
    @PersistenceContext
    protected EntityManager entityManager;

//    @Autowired
//    public AbstractDao(){
//    }

    @Override
    public void persist(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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

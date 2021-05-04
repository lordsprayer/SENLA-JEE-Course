package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateRequestDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class HibernateRequestDao extends HibernateAbstractDao<Request, Integer> implements IHibernateRequestDao {

    private static final Logger log = LogManager.getLogger(HibernateRequestDao.class);

    @Override
    public List<Request> getSortRequestsByTitle(EntityManager entityManager) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> root = query.from(Request.class);
            //root.fetch("books", JoinType.LEFT);
            query.orderBy(builder.asc(root.get("book").get("title")));
            TypedQuery<Request> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    public List<String> getSortRequestsByBookCount(EntityManager entityManager) {
        return null;
    }

    @Override
    protected Class<Request> getClazz() {
        return Request.class;
    }
}

package com.senla.courses;

import com.senla.courses.dbdao.IRequestDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RequestDao extends AbstractDao<Request, Integer> implements IRequestDao {

    private static final Logger log = LogManager.getLogger(RequestDao.class);

    @Override
    public List<Request> getSortRequestsByTitle() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> root = query.from(Request.class);
            Join<Request, Book>  infoJoin = root.join("book");
            query.orderBy(builder.asc(infoJoin.get("title")));
            TypedQuery<Request> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    public List<Request> getSortRequestsByBookCount() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(Request.class);
            Root<Request> root = query.from(Request.class);
            Join<Request, Book>  infoJoin = root.join("book");
            query.orderBy(builder.asc(builder.count(infoJoin.get("title")))).groupBy(infoJoin.get("title"));
            TypedQuery<Request> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    protected Class<Request> getClazz() {
        return Request.class;
    }
}

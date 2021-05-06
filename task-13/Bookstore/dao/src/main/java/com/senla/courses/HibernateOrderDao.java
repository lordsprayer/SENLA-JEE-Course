package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateOrderDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class HibernateOrderDao extends HibernateAbstractDao<Order, Integer> implements IHibernateOrderDao {

    private static final Logger log = LogManager.getLogger(HibernateOrderDao.class);

    @Override
    public List<Order> getSortOrders(String criterion, EntityManager entityManager) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = builder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Order> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    public List<Order> getSortCompleteOrders(String criterion, LocalDate date, EntityManager entityManager) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = builder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Predicate statusPredicate = builder.equal(root.get("status"), "COMPLETED");
            Predicate datePredicate = builder.lessThan(root.get("completionDate"), date);
            Predicate finalPredicate = builder.and(statusPredicate, datePredicate);
            query.where(finalPredicate).orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Order> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }
}
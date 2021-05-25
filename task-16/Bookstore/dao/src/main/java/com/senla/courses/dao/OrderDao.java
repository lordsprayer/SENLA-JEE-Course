package com.senla.courses.dao;

import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao<Order, Integer> implements IOrderDao {

    private static final Logger log = LogManager.getLogger(OrderDao.class);


    @Override
    public List<Order> getSortOrders(String criterion) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = builder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Order> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    public List<Order> getSortCompleteOrders(String criterion, LocalDate date) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = builder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            Predicate statusPredicate = builder.equal(root.get("status"), Order.Status.COMPLETED);
            Predicate datePredicate = builder.greaterThan(root.get("completionDate"), date);
            Predicate finalPredicate = builder.and(statusPredicate, datePredicate);
            query.where(finalPredicate).orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Order> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }
}

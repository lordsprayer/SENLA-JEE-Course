package com.senla.courses;

import com.senla.courses.dbdao.IBookDao;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class BookDao extends AbstractDao<Book, Integer> implements IBookDao {

    private static final Logger log = LogManager.getLogger(BookDao.class);
//    @Autowired
//    public BookDao(HibernateUtil util) {
//        super();
//    }

    public List<Book> getSortBook(String criterion){
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Book> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    public List<Book> getUnsoldBook(LocalDate date, String criterion) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Predicate availabilityPredicate = builder.equal(root.get("availability"), 1);
            Predicate datePredicate = builder.lessThan(root.get("receiptDate"), date);
            Predicate finalPredicate = builder.and(availabilityPredicate, datePredicate);
            query.select(root).where(finalPredicate).orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Book> unsoldSortQuery = entityManager.createQuery(query);
            return unsoldSortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new DaoException(SEARCH_ERROR, e);
        }
    }

    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }

    @Override
    public void insertOrder(Book book, Order order) {
        try {
            book.setOrder(order);
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw new DaoException(UPDATING_ERROR, e);
        }
    }
}

package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateBookDao;
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
public class HibernateBookDao extends HibernateAbstractDao<Book, Integer> implements IHibernateBookDao {

    private static final Logger log = LogManager.getLogger(HibernateBookDao.class);

    public List<Book> getSortBook(String criterion, EntityManager entityManager){
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.orderBy(builder.asc(root.get(criterion)));
            TypedQuery<Book> sortQuery = entityManager.createQuery(query);
            return sortQuery.getResultList();
        } catch (Exception e) {
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    public List<Book> getUnsoldBook(LocalDate date, String criterion, EntityManager entityManager) {
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
            log.log(Level.WARN, "Search showed no matches ");
            throw new DaoException("Search showed no matches", e);
        }
    }

    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }
//
//    @Override
//    public List<Book> getBookByOrder(Integer key, Connection connection) {
//        List<Book> list;
//        String sql = getSelectBookWhereOrder();
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setInt(1, key);
//            try (ResultSet rs = statement.executeQuery()) {
//                list = parseResultSet(rs);
//            }
//        } catch (Exception e) {
//            throw new DBException(e);
//        }
//        return list;
//    }
//
//    @Override
//    public void insertOrder(Book book, Integer orderId, Connection connection) {
//        String sql = getUpdateOrderQuery();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            prepareStatementForInsertOrder(statement, book, orderId);
//            int count = statement.executeUpdate();
//            if (count != 1) {
//                throw new DBException("On update modify more then 1 record: " + count);
//            }
//        } catch (Exception e) {
//            throw new DBException(e);
//        }
//    }
}

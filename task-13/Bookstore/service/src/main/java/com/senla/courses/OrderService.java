package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IBookDao;
import com.senla.courses.dbdao.IOrderDao;
import com.senla.courses.dbdao.IRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.service.IOrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class OrderService extends ConstantUtil implements IOrderService {

    private static final Logger log = LogManager.getLogger(OrderService.class.getName());
    @Inject
    private IBookDao bookDao;
    @Inject
    private IOrderDao orderDao;
    @Inject
    private IRequestDao requestDao;
    @Inject
    private HibernateUtil util;

    @Override
    public Order getById(Integer id) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Order order = orderDao.getByPK (id, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return order;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getAll() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return orders;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getSortOrders(String criterion) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.getSortOrders(criterion, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return orders;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void createOrder(Customer customer, List<Book> books, LocalDate creationDate){
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (Book book : books) {
                if (!book.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(book, date);
                    requestDao.persist(request, entityManager);
                }
            }
            Order order = new Order(customer, books, creationDate);
            orderDao.persist(order, entityManager);
            entityManager.flush();
            for (Book book : books) {
                bookDao.insertOrder(book, order, entityManager);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteOrder(Order order){
        EntityManager entityManager = util.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            orderDao.delete(order, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orderDao.update(order, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public Double countIncome(LocalDate date) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            double income = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    income += order.getTotalCost();
                }
            }
            return income;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Integer countCompleteOrders(LocalDate date) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            int count = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    count++;
                }
            }
            return count;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void orderDetails(Order order) {
        System.out.println(order);
    }

    @Override
    public void completeOrder(Order order, LocalDate date) {
        order.setStatus(Order.Status.COMPLETED);
        order.setCompletionDate(date);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orderDao.update(order, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getSortCompletedOrders(LocalDate date, String criterion) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.getSortCompleteOrders(criterion, date, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return orders;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

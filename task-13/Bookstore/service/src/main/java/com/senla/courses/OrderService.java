package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateBookDao;
import com.senla.courses.dbdao.IHibernateOrderDao;
import com.senla.courses.dbdao.IHibernateRequestDao;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IOrderService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class OrderService implements IOrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());
    @Inject
    private IHibernateBookDao bookDao;
    @Inject
    private IHibernateOrderDao orderDao;
    @Inject
    private IHibernateRequestDao requestDao;
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
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
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }
}

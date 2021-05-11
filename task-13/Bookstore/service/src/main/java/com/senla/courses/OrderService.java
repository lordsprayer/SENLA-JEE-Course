package com.senla.courses;

import com.senla.courses.dbdao.IBookDao;
import com.senla.courses.dbdao.IOrderDao;
import com.senla.courses.dbdao.IRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService extends ConstantUtil implements IOrderService {

    private static final Logger log = LogManager.getLogger(OrderService.class.getName());
    private final IBookDao bookDao;
    private final IOrderDao orderDao;
    private final IRequestDao requestDao;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Order getById(Integer id) {
        try {
            return orderDao.getByPK (id);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            return orderDao.getAll();
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getSortOrders(String criterion) {
        try {
            return orderDao.getSortOrders(criterion);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void createOrder(Customer customer, List<Book> books, LocalDate creationDate){
        try {
            for (Book book : books) {
                if (!book.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(book, date);
                    requestDao.persist(request);
                }
            }
            Order order = new Order(customer, books, creationDate);
            orderDao.persist(order);
            for (Book book : books) {
                bookDao.insertOrder(book, order);
            }
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteOrder(Order order){
        try{
            orderDao.delete(order);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public Double countIncome(LocalDate date) {
        try {
            List<Order> orders = orderDao.getAll();
            double income = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    income += order.getTotalCost();
                }
            }
            return income;
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Integer countCompleteOrders(LocalDate date) {
        try {
            List<Order> orders = orderDao.getAll();
            int count = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    count++;
                }
            }
            return count;
        } catch (DaoException e) {
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
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public List<Order> getSortCompletedOrders(LocalDate date, String criterion) {
        try {
            return orderDao.getSortCompleteOrders(criterion, date);
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

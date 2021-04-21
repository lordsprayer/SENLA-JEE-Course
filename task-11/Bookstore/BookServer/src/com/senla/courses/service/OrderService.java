package com.senla.courses.service;

import com.senla.courses.api.dbdao.IDBBookDao;
import com.senla.courses.api.dbdao.IDBOrderDao;
import com.senla.courses.api.dbdao.IDBRequestDao;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class OrderService implements IOrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());
    @Inject
    private IDBBookDao bookDao;
    @Inject
    private IDBOrderDao orderDao;
    @Inject
    private IDBRequestDao requestDao;
    @Inject
    private DBConnection dbConnection;

    @Override
    public Order getById(Integer id) {
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            Order order = orderDao.getByPK (id, connection);
            List<Book> books = new ArrayList<>(bookDao.getBookByOrder(order.getId(), connection));
            connection.commit();
            connection.setAutoCommit(true);
            order.setBookList(books);
            return order;
        } catch (DBException | SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders;
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            orders = new ArrayList<>(orderDao.getAll(connection));
            for (Order order : orders) {
                List<Book> books = new ArrayList<>(bookDao.getBookByOrder(order.getId(), connection));
                order.setBookList(books);
            }
            connection.commit();
            connection.setAutoCommit(true);
            return orders;
        } catch (DBException | SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Order> getSortOrders(String criterion) {
        List<Order> orders;
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            orders = new ArrayList<>(orderDao.getSortOrders(criterion, connection));
            for (Order order : orders) {
                order.setBookList(bookDao.getBookByOrder(order.getId(), connection));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return orders;
        } catch (DBException | SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public void createOrder(Customer customer, List<Book> books, LocalDate creationDate){
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            for (Book book : books) {
                if (!book.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(book, date);
                    requestDao.persist(request, connection);
                }
            }
            Order order = new Order(customer, books, creationDate);
            Order order1 = orderDao.persist(order, connection);
            for (Book book : books) {
                bookDao.insertOrder(book, order1.getId(), connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (DBException | SQLException e) {
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }

    @Override
    public void deleteOrder(Order order){
        try{
            orderDao.delete(order, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        try {
            orderDao.update(order, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public Double countIncome(LocalDate date) {
        try {
            List<Order> orders = new ArrayList<>(orderDao.getAll(dbConnection.getConnection()));
            double income = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    income += order.getTotalCost();
                }
            }
            return income;
        } catch (DBException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public Integer countCompleteOrders(LocalDate date) {
        try {
            List<Order> orders = new ArrayList<>(orderDao.getAll(dbConnection.getConnection()));
            int count = 0;
            for (Order order : orders) {
                if (order.getCompletionDate().compareTo(date) >= 0) {
                    count++;
                }
            }
            return count;
        } catch (DBException e) {
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
        try {
            orderDao.update(order, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public List<Order> getSortCompletedOrders(LocalDate date, String criterion) {
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            List<Order> orders = new ArrayList<>(orderDao.getSortCompleteOrders(criterion, date, connection));
            for (Order order : orders) {
                List<Book> books = new ArrayList<>(bookDao.getBookByOrder(order.getId(), connection));
                order.setBookList(books);
            }
            connection.commit();
            connection.setAutoCommit(true);
            return orders;
        } catch (DBException | SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }
}

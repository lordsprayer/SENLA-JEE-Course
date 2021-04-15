package com.senla.courses.service;

import com.senla.courses.api.dbdao.IDBBookDao;
import com.senla.courses.api.dbdao.IDBOrderDao;
import com.senla.courses.api.dbdao.IDBRequestDao;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        try {
            Connection connection = dbConnection.getConnection();
            connection.setAutoCommit(false);
            Order order = orderDao.getByPK (id, dbConnection.getConnection());
            //todo вмсето гетОлл написать метод в букдао, достающий книги из заказа
            List<Book> books = new ArrayList<>(bookDao.getAll(connection));
            connection.commit();
            connection.setAutoCommit(true);
            order.setBookList(books);
            return order;
        } catch (DaoException | SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll(dbConnection.getConnection());
    }

    @Override
    public List<Order> getSortOrders(String criterion) {
        return null;//orderDao.getSortOrders(criterion);
    }

    @Override
    public Order createOrder(Customer customer, List<Book> books, LocalDate creationDate){
        try {
            Connection connection = dbConnection.getConnection();
            connection.setAutoCommit(false);
            for (Book book : books) {
                if (!book.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(book, date);
                    requestDao.persist(request, connection);
                }
            }
            Order order = new Order(customer, books, creationDate);
            orderDao.persist(order, connection);
            //todo решить, будет ли персист возвращать последнее вставленное значение, либо написать отдельный метод
            Order order1 = orderDao.getByPK(1, connection);
            for (Book book : books) {
                bookDao.insertOrder(book, order1.getId(), connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            int[] booksId = new int[books.size()];
            for (int i = 0; i < books.size(); i++) {
                booksId[i] = books.get(i).getId();
                System.out.println(booksId[i]);
            }
        return order;
        } catch (SQLException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public void deleteOrder(Order order){
        orderDao.delete(order, dbConnection.getConnection());
    }

    @Override
    public void changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        orderDao.update(order, dbConnection.getConnection());
    }

    @Override
    public Double countIncome(LocalDate date) {
        List<Order> orders = new ArrayList<>(orderDao.getAll(dbConnection.getConnection()));
        double income= 0;
        for (Order order: orders){
            if(order.getCompletionDate().compareTo(date)>= 0){
                income += order.getTotalCost();
            }
        }
        return income;
    }

    @Override
    public Integer countCompleteOrders(LocalDate date) {
        List<Order> orders = new ArrayList<>(orderDao.getAll(dbConnection.getConnection()));
        int count= 0;
        for (Order order: orders){
            if(order.getCompletionDate().compareTo(date)>= 0){
                count ++;
            }
        }
        return count;
    }

    @Override
    public void orderDetails(Order order) {
        System.out.println(order);
    }

    @Override
    public void completeOrder(Order order, LocalDate date) {
        order.setStatus(Order.Status.COMPLETED);
        order.setCompletionDate(date);
        orderDao.update(order, dbConnection.getConnection());
    }

    @Override
    public List<Order> getSortCompletedOrders(Comparator<Order> comp, LocalDate date) {
        List<Order> orderList = new ArrayList<>(orderDao.getAll(dbConnection.getConnection()));
        orderList.sort(comp);
        return orderList.stream()
                .filter(o -> o.getStatus().equals(Order.Status.COMPLETED))
                .filter(o -> o.getCompletionDate().compareTo(date)>=0)
                .collect(Collectors.toList());
    }
}

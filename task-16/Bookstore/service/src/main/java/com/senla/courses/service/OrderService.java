package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IOrderDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.mappers.OrderMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService extends ConstantUtil implements IOrderService {

    private static final Logger log = LogManager.getLogger(OrderService.class.getName());
    private final IBookDao bookDao;
    private final IOrderDao orderDao;
    private final IRequestDao requestDao;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public OrderDto getById(Integer id) {
        try {
            Order order = orderDao.getByPK (id);
            return OrderMapper.INSTANCE.orderToOrderDto(order);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<OrderDto> getAll() {
        try {
            List<Order> orders = orderDao.getAll();
            return Converter.convertOrders(orders);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<OrderDto> getSortOrders(String criterion) {
        try {
            List<Order> orders = orderDao.getSortOrders(criterion);
            return Converter.convertOrders(orders);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void createOrder(CustomerDto customerDto, List<BookDto> books, LocalDate creationDate){
        try {
            List<Book> bookList = Converter.convertBooksDto(books);
            for (Book book : bookList) {
                if (!book.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(book, date);
                    requestDao.persist(request);
                }
            }
            Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDto);
            Order order = new Order(customer, bookList, creationDate);
            orderDao.persist(order);
            for (Book book : bookList) {
                bookDao.insertOrder(book, order);
            }
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteOrder(Integer id){
        try{
            Order order = orderDao.getByPK(id);
            orderDao.delete(order);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void changeStatus(OrderDto orderDto, String status) {
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        order.setStatus(Order.Status.valueOf(status));
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

    //todo заменить в реализации на получить по айди или удалить совсем
    @Override
    public void orderDetails(OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        System.out.println(order);
    }

    @Override
    public void completeOrder(OrderDto orderDto, LocalDate date) {
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
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
    public List<OrderDto> getSortCompletedOrders(LocalDate date, String criterion) {
        try {
            List<Order> orders = orderDao.getSortCompleteOrders(criterion, date);
            return Converter.convertOrders(orders);
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.ICustomerDao;
import com.senla.courses.dao.IOrderDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.exception.DaoException;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService extends ConstantUtil implements IOrderService {

    private static final Logger log = LogManager.getLogger(OrderService.class.getName());
    private final IBookDao bookDao;
    private final IOrderDao orderDao;
    private final IRequestDao requestDao;
    private final ICustomerDao customerDao;

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
    public void createOrder(Integer customerId, List<Integer> books){
        try {
            List<Book> bookList = new ArrayList<>();
            Book book;
            for(Integer bookId : books) {
                book = bookDao.getByPK(bookId);
                bookList.add(book);
            }
            for (Book bookOfRequest : bookList) {
                if (!bookOfRequest.getAvailability()) {
                    LocalDate date = LocalDate.now();
                    Request request = new Request(bookOfRequest, date);
                    requestDao.persist(request);
                }
            }
            Customer customer = customerDao.getByPK(customerId);
            Order order = new Order(customer, bookList, LocalDate.now());
            orderDao.persist(order);
            for (Book bookOfRequest : bookList) {
                bookDao.insertOrder(bookOfRequest, order);
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
    public void changeStatus(Integer id, String status) {
        Order order = orderDao.getByPK(id);
        order.setStatus(Order.Status.valueOf(status));
        if(order.getStatus().equals(Order.Status.COMPLETED)) {
            order.setCompletionDate(LocalDate.now());
        } else {
            order.setCompletionDate(LocalDate.of(1970, 1, 1));
        }
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
    public List<OrderDto> getSortCompletedOrders(LocalDate date, String criterion) {
        try {
            List<Order> orders = orderDao.getSortCompleteOrders(criterion, date);
            return Converter.convertOrders(orders);
        } catch (DaoException e) {
            log.log (Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

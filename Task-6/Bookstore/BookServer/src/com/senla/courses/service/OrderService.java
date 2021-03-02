package com.senla.courses.service;

import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());
    private final IOrderDao orderDao;
    private final IRequestService requestService;

    public OrderService(IOrderDao orderDao, IRequestService requestService) {
        this.orderDao = orderDao;
        this.requestService = requestService;
    }

    @Override
    public Order getById(Long id) {
        try {
            return orderDao.getById (id);
        } catch (DaoException e) {
            log.log (Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches");
        }
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getSortOrders(Comparator<Order> comp) {
        return orderDao.getSortOrders(comp);
    }

    @Override
    public Order createOrder(Customer customer, List<Book> books, LocalDate creationDate) {
        for(Book book : books){
            if (!book.getAvailability()){
                requestService.createRequest(book);
            }
        }
        Order order = new Order(customer, books, creationDate);
        orderDao.save(order);
        return order;
    }

    @Override
    public void cancelOrder(Order order){
        orderDao.delete(order);
    }

    @Override
    public void changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        orderDao.update(order);
    }

    @Override
    public Double countIncome(LocalDate date) {
        List<Order> orders = new ArrayList<>(orderDao.getAll());
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
        List<Order> orders = new ArrayList<>(orderDao.getAll());
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
        orderDao.update(order);
    }

    @Override
    public List<Order> getSortCompletedOrders(Comparator<Order> comp, LocalDate date) {
        List<Order> orderList = new ArrayList<>(orderDao.getAll());
        orderList.sort(comp);
        return orderList.stream()
                .filter(o -> o.getStatus().equals(Order.Status.COMPLETED))
                .filter(o -> o.getCompletionDate().compareTo(date)>=0)
                .collect(Collectors.toList());
    }
}

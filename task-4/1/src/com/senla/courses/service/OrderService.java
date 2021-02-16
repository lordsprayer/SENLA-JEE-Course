package com.senla.courses.service;

import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IRequestService requestService;

    public OrderService(IOrderDao orderDao, IRequestService requestService) {
        this.orderDao = orderDao;
        this.requestService = requestService;
    }

    @Override
    public Order createOrder(Customer customer, List<Book> books, LocalDate creationDate) {
        //List<Book> list = new ArrayList<Book>();
        for(Book book : books){
            //list.add(book);
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
    public Order changeStatus(Order order, Order.Status status) {
        order.setStatus(status);
        orderDao.update(order);
        return order;
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
    public void orderDeatails(Order order) {
        System.out.println(order);
    }

    @Override
    public Order completeOrder(Order order) {
        order.setStatus(Order.Status.COMPLETED);
        orderDao.update(order);
        return order;
    }
}

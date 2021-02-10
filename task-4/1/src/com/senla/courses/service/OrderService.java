package com.senla.courses.service;

import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IRequestDao requestDao;
    private final IRequestService requestService;

    public OrderService(IOrderDao orderDao, IRequestDao requestDao, IRequestService requestService) {
        this.orderDao = orderDao;
        this.requestDao = requestDao;
        this.requestService = requestService;
    }

    @Override
    public Order createOrder(List<Book> books, LocalDate creationDate) {
        //List<Book> list = new ArrayList<Book>();
        for(Book book : books){
            //list.add(book);
            if (book.getAvailability()==false){
                requestService.createRequest(book);
            }
        }
        Order order = new Order(books, creationDate);
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
}

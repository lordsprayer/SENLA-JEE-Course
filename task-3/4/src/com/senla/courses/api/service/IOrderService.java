package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Order;

public interface IOrderService {

    Order createOrder(Book... book);
    void cancelOrder(Order order);
    Order changeStatus(Order order, Order.Status status);
}

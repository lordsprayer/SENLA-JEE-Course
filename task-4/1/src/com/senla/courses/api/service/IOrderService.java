package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    Order createOrder(List<Book> books, LocalDate creationDate);
    void cancelOrder(Order order);
    Order changeStatus(Order order, Order.Status status);
}

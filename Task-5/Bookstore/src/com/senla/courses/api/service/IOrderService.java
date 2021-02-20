package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    Order createOrder(Customer customer, List<Book> books, LocalDate creationDate);
    void cancelOrder(Order order);
    Order changeStatus(Order order, Order.Status status);
    Double countIncome (LocalDate date);
    Integer countCompleteOrders(LocalDate date);
    void orderDeatails (Order order);
    Order completeOrder(Order order);
}

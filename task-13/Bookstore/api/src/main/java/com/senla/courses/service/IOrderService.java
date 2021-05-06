package com.senla.courses.service;

import com.senla.courses.Customer;
import com.senla.courses.Book;
import com.senla.courses.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    Order getById(Integer id);
    List<Order> getAll();
    List<Order> getSortOrders(String criterion);
    void createOrder(Customer customer, List<Book> books, LocalDate creationDate);
    void deleteOrder(Order order);
    void changeStatus(Order order, Order.Status status);
    Double countIncome (LocalDate date);
    Integer countCompleteOrders(LocalDate date);
    void orderDetails (Order order);
    void completeOrder(Order order, LocalDate date);
    List<Order> getSortCompletedOrders(LocalDate date, String criterion);
}
package com.senla.courses.api.service;

import com.senla.courses.model.Customer;
import com.senla.courses.model.Book;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface IOrderService {

    Order getById(Integer id);
    List<Order> getAll();
    List<Order> getSortOrders(String criterion);
    Order createOrder(Customer customer, List<Book> books, LocalDate creationDate);
    void deleteOrder(Order order);
    void changeStatus(Order order, Order.Status status);
    Double countIncome (LocalDate date);
    Integer countCompleteOrders(LocalDate date);
    void orderDetails (Order order);
    void completeOrder(Order order, LocalDate date);
    List<Order> getSortCompletedOrders(Comparator<Order> comp, LocalDate date);
    //void saveAll();

}

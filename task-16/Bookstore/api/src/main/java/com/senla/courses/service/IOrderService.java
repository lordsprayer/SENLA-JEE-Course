package com.senla.courses.service;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.OrderDto;


import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    OrderDto getById(Integer id);
    List<OrderDto> getAll();
    List<OrderDto> getSortOrders(String criterion);
    void createOrder(CustomerDto customer, List<BookDto> books, LocalDate creationDate);
    void deleteOrder(OrderDto order);
    void changeStatus(OrderDto order, String status);
    Double countIncome (LocalDate date);
    Integer countCompleteOrders(LocalDate date);
    void orderDetails (OrderDto order);
    void completeOrder(OrderDto order, LocalDate date);
    List<OrderDto> getSortCompletedOrders(LocalDate date, String criterion);
}

package com.senla.courses.service;

import com.senla.courses.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    OrderDto getOrderById(Integer id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getSortOrders(String criterion);
    void createOrder(Integer customerId, List<Integer> books);
    void deleteOrder(Integer id);
    void changeStatus(Integer id, String status);
    Double countIncome (LocalDate date);
    Integer countCompleteOrders(LocalDate date);
    List<OrderDto> getSortCompletedOrders(LocalDate date, String criterion);
    List<OrderDto> getAllOrders(LocalDate date, String criterion);
}

package com.senla.courses.dao;

import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderDao extends GenericDao<Order, Integer> {
    List<Order> getSortOrders(String criterion);
    List<Order> getSortCompleteOrders(String criterion, LocalDate date);
}

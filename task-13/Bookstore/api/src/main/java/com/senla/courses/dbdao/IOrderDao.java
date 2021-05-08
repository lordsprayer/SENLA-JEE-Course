package com.senla.courses.dbdao;

import com.senla.courses.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderDao extends GenericDao<Order, Integer> {
    List<Order> getSortOrders(String criterion);
    List<Order> getSortCompleteOrders(String criterion, LocalDate date);
}

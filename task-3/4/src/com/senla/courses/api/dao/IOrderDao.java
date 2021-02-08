package com.senla.courses.api.dao;

import com.senla.courses.model.Order;

import java.util.List;

public interface IOrderDao {

    void save(Order order);
    void delete(Order order);
    Order update(Order order);
    Order getById(Long id);
    List<Order> getAll();
}

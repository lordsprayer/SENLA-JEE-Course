package com.senla.courses.api.dbdao;

import com.senla.courses.model.Order;

import java.sql.Connection;
import java.util.List;

public interface IDBOrderDao extends GenericDao<Order, Integer> {
    List<Order> getSortOrders(String criterion, Connection connection);
}

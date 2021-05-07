package com.senla.courses.dbdao;

import com.senla.courses.Order;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public interface IOrderDao extends GenericDao<Order, Integer> {
    List<Order> getSortOrders(String criterion, EntityManager entityManager);
    List<Order> getSortCompleteOrders(String criterion, LocalDate date, EntityManager entityManager);
}

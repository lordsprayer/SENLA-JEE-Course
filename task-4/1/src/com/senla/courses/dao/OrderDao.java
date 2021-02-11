package com.senla.courses.dao;

import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.model.Book;
import com.senla.courses.model.Order;
import com.senla.courses.util.IdGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDao implements IOrderDao {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        order.setId(IdGenerator.GenerateOrderId());
        orders.add(order);
    }

    @Override
    public void delete(Order order) {
        orders.remove(order);
    }

    @Override
    public Order update(Order order) {
        Order order1 = getById(order.getId());
        order1.setBookList(order.getBookList());
        order1.setCreationDate(order.getCreationDate());
        order1.setCompletionDate(order.getCompletionDate());
        order1.setTotalCost(order.getTotalCost());
        order.setStatus(order1.getStatus());
        return order1;
    }

    @Override
    public Order getById(Long id) {
        for(Order order : orders){
            if (id.equals(order.getId())){
                return order;
            }
        }return null;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public List<Order> getSortOrders(Comparator<Order> comp) {
        List<Order> orderList = new ArrayList<>(orders);
        orderList.sort(comp);
        return orderList;
    }

    @Override
    public List<Order> getSortCompletedOrders(Comparator<Order> comp) {
        List<Order> orderList = new ArrayList<>(orders);
        orderList.sort(comp);
        return orderList.stream()
                .filter(o -> o.getStatus().equals(Order.Status.COMPLETED))
                .collect(Collectors.toList());
    }
}

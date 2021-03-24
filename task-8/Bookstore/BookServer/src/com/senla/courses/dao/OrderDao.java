package com.senla.courses.dao;

import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Order;
import com.senla.courses.util.SerializationHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class OrderDao implements IOrderDao {

    private static final String GET_BY_ID_ERROR_MESSAGE = "Could not find an order by id: %d";
    private static final Logger log = Logger.getLogger(BookDao.class.getName());
    private static List<Order> orders;

    public OrderDao() {
        try{
            orders = SerializationHandler.deserialize(Order.class);
        } catch(Exception e){
            orders = new ArrayList<>();}
    }

    @Override
    public void save(Order order) {
        try {
            order.setId(orders.get(orders.size() - 1).getId() + 1L);
        } catch (Exception e){
            order.setId(1L);
        }
        orders.add(order);
    }

    @Override
    public void delete(Order order) {
        orders.remove(order);
    }

    @Override
    public Order update(Order order) {
        Order order1 = getById(order.getId());
        order1.setCustomer(order.getCustomer());
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
        }
        log.log(Level.WARNING, String.format(GET_BY_ID_ERROR_MESSAGE, id));
        throw new DaoException(String.format(GET_BY_ID_ERROR_MESSAGE, id));
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
}

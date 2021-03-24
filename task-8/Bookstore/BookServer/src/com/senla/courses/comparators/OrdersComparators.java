package com.senla.courses.comparators;

import com.senla.courses.model.Order;

import java.util.Comparator;

public class OrdersComparators {

    //компаратор по дате выполнения заказа
    public static Comparator<Order> DateComparator = Comparator.comparing(Order::getCompletionDate);

    //компаратор по стоимости заказа
    public static Comparator<Order> TotalCostComparator = Comparator.comparing(Order::getTotalCost);

    //компаратор по статусу заказа
    public static Comparator<Order> StatusComparator = Comparator.comparingInt(o -> o.getStatus().getSeverity());
}

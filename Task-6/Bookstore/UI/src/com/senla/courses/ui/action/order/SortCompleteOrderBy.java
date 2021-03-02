package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.time.LocalDate;

public class SortCompleteOrderBy extends AbstractAction implements IAction {
    private final int index;

    public SortCompleteOrderBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        System.out.println("Введите дату:");
        System.out.println("Введите год ");
        int year = scan.nextInt();
        System.out.println("Введите месяц ");
        int month = scan.nextInt();
        System.out.println("Введите день ");
        int day = scan.nextInt();
        LocalDate date = LocalDate.of(year, month,day);
        facade.sortCompleteOrders(facade.createOrderComparators().get(index), date);
    }
}

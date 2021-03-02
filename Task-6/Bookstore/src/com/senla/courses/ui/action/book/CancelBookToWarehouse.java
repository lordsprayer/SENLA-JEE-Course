package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class CancelBookToWarehouse extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printBooksByAvailability(true);
        System.out.println("Введите id книги");
        int id = scan.nextInt();
        facade.cancelBookToWarehouse(facade.getBookById((long)id));
        System.out.println("Книга списана");
        facade.printBook((long)id);
    }
}

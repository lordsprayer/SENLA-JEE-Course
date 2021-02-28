package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class DeleteBook extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllBook();
        System.out.println("Введите id книги");
        int id = scan.nextInt();
        facade.deleteBook((long)id);
        System.out.println("Книга удалена");
    }
}

package com.senla.courses.ui.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class DeleteBook extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            facade.printAllBook();
            int id = IntNumberValidation.validation("Введите id книги");
            try {
                facade.deleteBook((long) id);
                System.out.println("Книга удалена");
            } catch (ServiceException e){
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}

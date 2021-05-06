package com.senla.courses.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import org.apache.logging.log4j.Level;

public class SetBookDescription extends AbstractAction implements IAction {

    public SetBookDescription(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            facade.printAllBook();
            int id = IntNumberValidation.validation("Введите id книги");
            System.out.println("Введите описание");
            String description = scan.nextLine();
            try {
                facade.setBookDescription(facade.getBookById(id), description);
                System.out.println("Описание добавлено");
            } catch (ServiceException e){
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}

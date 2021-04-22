package com.senla.courses.ui.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class GetBookDescription extends AbstractAction implements IAction {

    public GetBookDescription(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            facade.printAllBook();
            int id = IntNumberValidation.validation("Введите id книги");
            try{
               if(facade.getBookDescription(id) == null) {
                   System.out.println("У книги пока нет описания");
               }
            } catch (ServiceException e){
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}

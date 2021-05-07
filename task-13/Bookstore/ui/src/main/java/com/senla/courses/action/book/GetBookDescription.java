package com.senla.courses.action.book;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

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
            } catch (DaoException e){
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}

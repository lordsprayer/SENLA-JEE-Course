package com.senla.courses.action.book;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class DeleteBook extends AbstractAction implements IAction {

    public DeleteBook(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            facade.printAllBook();
            int id = IntNumberValidation.validation("Введите id книги");
            try {
                facade.deleteBook(id);
                System.out.println("Книга удалена");
            } catch (DaoException e){
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}

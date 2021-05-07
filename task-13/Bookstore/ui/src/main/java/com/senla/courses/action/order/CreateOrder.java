package com.senla.courses.action.order;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder extends AbstractAction implements IAction {

    public CreateOrder(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("На складе нет книг для заказа");
        } else {
            facade.printAllBook();
            List<Integer> books = new ArrayList<>();
            int book = IntNumberValidation.validation("Введите id книги");
            books.add(book);
            while (true) {
                System.out.println("Выберите ещё книги либо завершите выбор(нажмите 0): ");
                book = IntNumberValidation.validation("Ведите id книги");
                if (book == 0) {
                    break;
                }
                books.add(book);
            }
            int customer = 0;
            if(facade.getAllCustomers().isEmpty()){
                System.out.println("В базе нет покупателей");
            } else {
                facade.printAllCustomers();
                customer = IntNumberValidation.validation("Введите id покупателя");
            }
            try {
                facade.createOrder(facade.getCustomerById(customer), facade.createBookList(books));
                System.out.println("Заказ создан");
            } catch (DaoException e){
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Заказ не был создан, так как не существует книги либо покупателя с таким id ");
            }
        }
    }
}

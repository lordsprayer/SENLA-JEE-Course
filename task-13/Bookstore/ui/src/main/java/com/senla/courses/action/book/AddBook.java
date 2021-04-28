package com.senla.courses.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import java.util.Scanner;
import java.util.logging.Level;

public class AddBook extends AbstractAction implements IAction {

    public AddBook(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите название");
            String title = scan.nextLine();
            System.out.println("Введите автора");
            String author = scan.nextLine();
            System.out.println("Введите год публикации");
            Integer publicationYear = Integer.parseInt(scan.next());
            System.out.println("Введите стоимость");
            Double cost = Double.parseDouble(scan.next());
            System.out.println("Введите дату поступления");
            try{
                facade.saveBook(facade.createBook(title, author, publicationYear, cost, facade.createDate(), true));
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Ошибка БД");
            }
            System.out.println("Книга добавлена");
        } catch (Exception e){
            System.out.println("Ошибка ввода данных");
        }
    }
}

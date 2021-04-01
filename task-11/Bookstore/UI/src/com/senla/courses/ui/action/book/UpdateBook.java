package com.senla.courses.ui.action.book;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

public class UpdateBook extends AbstractAction implements IAction {

    public UpdateBook(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            try {
                facade.printAllBook();
                int id = IntNumberValidation.validation("Введите id книги ");
                System.out.println("Введите название ");
                String title = scan.next();
                System.out.println("Введите автора ");
                String author = scan.next();
                System.out.println("Введите год публикации ");
                Integer publicationYear = IntNumberValidation.validation("Введите год публикации");
                System.out.println("Введите стоимость ");
                Double cost = Double.parseDouble(scan.next());
                System.out.println("Введите дату поступления ");
                facade.updateBook(facade.createBook(title, author, publicationYear, cost, facade.createDate(), true), (long) id);
                System.out.println("Книга обновлена");
                facade.printBook((long) id);
            } catch (Exception e){
                System.out.println("Введены неверные данные");
            }
        }
    }
}

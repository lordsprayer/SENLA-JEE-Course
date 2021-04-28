package com.senla.courses.action.book;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.IAction;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.validation.IntNumberValidation;

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
                String title = scan.nextLine();
                System.out.println("Введите автора ");
                String author = scan.nextLine();
                Integer publicationYear = IntNumberValidation.validation("Введите год публикации");
                System.out.println("Введите стоимость ");
                Double cost = Double.parseDouble(scan.next());
                System.out.println("Введите дату поступления ");
                facade.updateBook(facade.createBook(title, author, publicationYear, cost, facade.createDate(), true), id);
                System.out.println("Книга обновлена");
                facade.printBook(id);
            } catch (Exception e){
                System.out.println("Введены неверные данные");
            }
        }
    }
}

package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class UpdateBook extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllBook();
        System.out.println("Введите id книги ");
        int id = scan.nextInt();
        System.out.println("Введите название ");
        String title = scan.next();
        System.out.println("Введите автора ");
        String author = scan.next();
        System.out.println("Введите год публикации ");
        Integer publicationYear = scan.nextInt();
        System.out.println("Введите стоимость ");
        Double cost = scan.nextDouble();
        System.out.println("Введите дату поступления ");
        facade.updateBook(facade.createBook(title, author, publicationYear, cost, facade.createDate(), true), (long)id);
        System.out.println("Книга обновлена");
        facade.printBook((long)id);
    }
}

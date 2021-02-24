package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class AddBook extends AbstractAction implements IAction {

    @Override
    public void execute() {
        System.out.println("Введите название");
        String title = scan.nextLine();
        System.out.println("Введите автора");
        String author = scan.nextLine();
        System.out.println("Введите год публикации");
        Integer publicationYear = scan.nextInt();
        System.out.println("Введите стоимость");
        Double cost = scan.nextDouble();
        System.out.println("Введите дату поступления");
        facade.saveBook(facade.createBook(title, author, publicationYear, cost, facade.createDate(), true));
        System.out.println("Книга добавлена");
    }
}

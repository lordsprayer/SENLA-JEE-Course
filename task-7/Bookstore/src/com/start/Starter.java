package com.start;

import com.senla.courses.config.CustomLogger;
import com.senla.courses.model.Book;
import com.senla.courses.ui.menu.MenuController;
import com.senla.courses.util.PropertiesHandler;
import com.senla.courses.util.SerializationHandler;

import java.time.LocalDate;
import java.util.List;

public class Starter {


    public static void main(String[] args) {
        new CustomLogger();
//        Boolean prop = PropertiesHandler.getProperties("permit_closing_request")
//                .map(Boolean::valueOf).orElse(false);
//        System.out.println(prop);
//        List<Book> b1 = new java.util.ArrayList<>(List.of(new Book("Страж", "Алексей Пехов", 2019, 17.5, LocalDate.of(2020, 9, 21), true)));
//        Book book3 = new Book("Бессмертный", "Кэтрин Валенте", 2018, 12.3, LocalDate.of(2020, 7, 28), false);
//        b1.add(book3);
//        SerializationHandler.serialize(b1);
//        List<Book> b2= SerializationHandler.deserialize(Book.class);
//        System.out.println(b2);
        MenuController.getInstance().run();

    }

}

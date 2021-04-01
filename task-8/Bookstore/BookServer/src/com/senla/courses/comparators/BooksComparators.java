package com.senla.courses.comparators;

import com.senla.courses.model.Book;

import java.util.Comparator;

public class BooksComparators {

    public static Comparator<Book> NameComparator = Comparator.comparing(Book::getTitle);

    public static Comparator<Book> CostComparator = Comparator.comparing(Book::getCost);

    public static Comparator<Book> AvailabilityComparator = (b1, b2) -> {
        if (b1.getAvailability() == b2.getAvailability()) {
            return 0;
        }
        if (b1.getAvailability() && !b2.getAvailability()) {
            return -1;
        }
        else {
            return 1;
        }
    };

    public static Comparator<Book> PublicationComparator = Comparator.comparing(Book::getPublicationYear);

    public static Comparator<Book> ReceiptComparator = Comparator.comparing(Book::getReceiptDate);
}

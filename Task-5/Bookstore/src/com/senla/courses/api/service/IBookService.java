package com.senla.courses.api.service;

import com.senla.courses.model.Book;

import java.util.List;

public interface IBookService {

    Book cancelBook(Book book);
    Book addBook(Book book);
    List<Book> unsoldBook();
    void lookDescription(Book book);
}

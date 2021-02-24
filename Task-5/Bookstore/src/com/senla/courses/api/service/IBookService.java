package com.senla.courses.api.service;

import com.senla.courses.model.Book;

import java.util.List;

public interface IBookService {

    void cancelBook(Book book);
    void addBook(Book book);
    List<Book> unsoldBook();
    void lookDescription(Book book);
}

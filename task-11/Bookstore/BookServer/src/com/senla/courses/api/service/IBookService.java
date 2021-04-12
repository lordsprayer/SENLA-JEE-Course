package com.senla.courses.api.service;

import com.senla.courses.model.Book;

import java.util.Comparator;
import java.util.List;

public interface IBookService {

    List<Book> getAll();
    Book getById(Integer id);
    void save(Book book);
    void delete(Book book);
    void update(Book book);
    void cancelBook(Book book);
    void addBook(Book book);
    List<Book> unsoldBook(String criterion);
    String getDescription(Book book);
    List<Book> getSortBooks(String criterion);
}

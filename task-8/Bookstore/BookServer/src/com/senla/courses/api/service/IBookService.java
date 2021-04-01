package com.senla.courses.api.service;

import com.senla.courses.model.Book;

import java.util.Comparator;
import java.util.List;

public interface IBookService {

    List<Book> getAll();
    Book getById(Long id);
    void save(Book book);
    void delete(Book book);
    Book update(Book book);
    void cancelBook(Book book);
    void addBook(Book book);
    List<Book> unsoldBook();
    String getDescription(Book book);
    List<Book> getSortBooks(Comparator<Book> comp);
    void saveAll();
}

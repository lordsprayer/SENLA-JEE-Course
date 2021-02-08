package com.senla.courses.api.dao;

import com.senla.courses.model.Book;

import java.util.List;

public interface IBookDao {

    void save(Book book);
    void delete(Book book);
    Book update(Book book);
    Book getById(Long id);
    List<Book> getAll();
}

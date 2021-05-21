package com.senla.courses.service;

import com.senla.courses.dto.BookDto;

import java.util.List;

public interface IBookService {

    List<BookDto> getAll();
    BookDto getById(Integer id);
    void save(BookDto book);
    void delete(Integer id);
    void update(BookDto book);
    void cancelBook(BookDto book);
    void addBook(BookDto book);
    List<BookDto> unsoldBook(String criterion);
    String getDescription(BookDto book);
    List<BookDto> getSortBooks(String criterion);
}

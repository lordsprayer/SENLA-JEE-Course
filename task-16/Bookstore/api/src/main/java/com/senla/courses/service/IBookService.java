package com.senla.courses.service;

import com.senla.courses.dto.BookDto;

import java.util.List;

public interface IBookService {

    List<BookDto> getAll();
    BookDto getById(Integer id);
    void save(BookDto book);
    void delete(Integer id);
    void update(BookDto book);
    void cancelBook(Integer id);
    void addBook(Integer id);
    List<BookDto> unsoldBook(String criterion);
    List<BookDto> getSortBooks(String criterion);
}

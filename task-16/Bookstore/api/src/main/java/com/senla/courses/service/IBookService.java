package com.senla.courses.service;

import com.senla.courses.dto.BookDto;

import java.util.List;

public interface IBookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Integer id);
    void createBook(BookDto book);
    void deleteBook(Integer id);
    void updateBook(BookDto book);
    void cancelBook(Integer id);
    void addBook(Integer id);
    List<BookDto> unsoldBook(String criterion);
    List<BookDto> getSortBooks(String criterion);
    void addOrDeleteBook(Integer id, Boolean availability);
}

package com.senla.courses.api.service;

import com.senla.courses.model.Book;

public interface IBookService {

    Book cancelBook(Book book);
    Book addBook(Book book);
}

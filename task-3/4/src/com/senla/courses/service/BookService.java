package com.senla.courses.service;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    private final IBookDao bookDao;
    private final IRequestDao requestDao;
    private final IRequestService requestService;
    public BookService(IBookDao bookDao, IRequestDao requestDao, IRequestService requestService) {
        this.bookDao = bookDao;
        this.requestDao = requestDao;
        this.requestService = requestService;
    }

    @Override
    public Book cancelBook(Book book) {
        book.setAvailability(false);
        bookDao.update(book);
        return book;
    }

    @Override
    public Book addBook(Book book) {
        book.setAvailability(true);
        bookDao.update(book);
        List<Request> requests= new ArrayList<Request>(requestDao.getAll());
        for (Request request : requests){
            if(request.getBook().equals(book)){
                requestService.closeRequest(request);
            }
        }
        return book;
    }


}

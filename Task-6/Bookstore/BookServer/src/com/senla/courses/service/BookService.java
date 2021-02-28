package com.senla.courses.service;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void cancelBook(Book book) {
        book.setAvailability(false);
        bookDao.update(book);
    }

    @Override
    public void addBook(Book book) {
        book.setAvailability(true);
        bookDao.update(book);
        List<Request> requests= new ArrayList<>(requestDao.getAll());
        for (Request request : requests){
            if(request.getBook().equals(book)){
                requestService.closeRequest(request);
            }
        }
    }

    @Override
    public List<Book> unsoldBook() {
        List<Book> books= new ArrayList<>(bookDao.getAll());
        LocalDate date = LocalDate.now().minusMonths(6);
        return books.stream().filter(book -> book.getReceiptDate().compareTo(date)<=0).collect(Collectors.toList());
    }

    @Override
    public void lookDescription(Book book) {
        System.out.println(book.getDescription());
    }
}

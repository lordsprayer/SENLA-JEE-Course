package com.senla.courses.service;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookService implements IBookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());
    private final IBookDao bookDao;
    private final IRequestDao requestDao;
    private final IRequestService requestService;
    public BookService(IBookDao bookDao, IRequestDao requestDao, IRequestService requestService) {
        this.bookDao = bookDao;
        this.requestDao = requestDao;
        this.requestService = requestService;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(Long id) {
        try{
            return bookDao.getById(id);
        } catch (DaoException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches");
        }
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public Book update(Book book) {
        return bookDao.update(book);
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
        List<Book> books= new ArrayList<>(getAll());
        LocalDate date = LocalDate.now().minusMonths(6);
        return books.stream().filter(book -> book.getReceiptDate().compareTo(date)<=0).collect(Collectors.toList());
    }

    @Override
    public void lookDescription(Book book) {
        System.out.println(book.getDescription());
    }

    @Override
    public List<Book> getSortBooks(Comparator<Book> comp) {
        return bookDao.getSortBooks(comp);
    }
}

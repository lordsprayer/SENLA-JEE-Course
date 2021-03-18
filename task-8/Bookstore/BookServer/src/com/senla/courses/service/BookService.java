package com.senla.courses.service;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
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

@Singleton
public class BookService implements IBookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());
    @Inject
    private IBookDao bookDao;
    @Inject
    private IRequestDao requestDao;
    @Inject
    private IRequestService requestService;

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
    public void addBook(Book book, Boolean permit) {
        book.setAvailability(true);
        bookDao.update(book);
        if(permit) {
            List<Request> requests = new ArrayList<>(requestDao.getAll());
            for (Request request : requests) {
                if (request.getBook().equals(book)) {
                    requestService.closeRequest(request);
                }
            }
        }
    }

    @Override
    public List<Book> unsoldBook(Integer months) {
        List<Book> books= new ArrayList<>(getAll());
        LocalDate date = LocalDate.now().minusMonths(months);
        return books.stream().filter(book -> book.getReceiptDate().compareTo(date)<=0).collect(Collectors.toList());
    }

    @Override
    public String getDescription(Book book) {
        System.out.println(book.getDescription());
        return book.getDescription();
    }

    @Override
    public List<Book> getSortBooks(Comparator<Book> comp) {
        return bookDao.getSortBooks(comp);
    }

    @Override
    public void saveAll() {
        bookDao.saveAll();
    }
}

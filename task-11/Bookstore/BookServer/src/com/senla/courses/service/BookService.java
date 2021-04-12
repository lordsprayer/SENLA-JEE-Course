package com.senla.courses.service;

import com.senla.courses.api.dbdao.IDBBookDao;
import com.senla.courses.api.dbdao.IDBRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.ConfigProperty;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class BookService implements IBookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());
    @Inject
    private IDBBookDao bookDao;
    @Inject
    private IDBRequestDao requestDao;
    @Inject
    private IRequestService requestService;
    @ConfigProperty(propertyName = "number_of_months")
    private Integer months;
    @ConfigProperty(propertyName = "permit_closing_request")
    private Boolean permit;
    @Inject
    private DBConnection dbConnection;

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(Integer id) {
        try{
            return bookDao.getByPK(id);
        } catch (DaoException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public void save(Book book) {
        bookDao.persist(book);
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
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
        if(permit) {
            List<Request> requests = new ArrayList<>(requestDao.getAll());
            for (Request request : requests) {
                if (request.getBook().equals(book)) {
                    requestService.closeRequest(request);
                }
            }
        }
        else
            log.log(Level.INFO, "Automatic closing of requests is prohibited");
    }


    @Override
    public List<Book> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        return bookDao.getUnsoldBook(date,criterion);
    }

    @Override
    public String getDescription(Book book) {
        System.out.println(book.getDescription());
        return book.getDescription();
    }

    @Override
    public List<Book> getSortBooks(String criterion) {
        return bookDao.getSortBook(criterion);
    }
}

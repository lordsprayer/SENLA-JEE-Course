package com.senla.courses;

import com.senla.courses.dbdao.IDBBookDao;
import com.senla.courses.dbdao.IDBRequestDao;
import com.senla.courses.api.annotation.ConfigProperty;
import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IBookService;

import java.sql.Connection;
import java.sql.SQLException;
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
    @ConfigProperty(propertyName = "number_of_months")
    private Integer months;
    @ConfigProperty(propertyName = "permit_closing_request")
    private Boolean permit;
    @Inject
    private DBConnection dbConnection;

    @Override
    public List<Book> getAll() {
        try {
            return bookDao.getAll(dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public Book getById(Integer id) {
        try{
            return bookDao.getByPK(id, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public void save(Book book) {
        try {
            bookDao.persist(book, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }

    @Override
    public void delete(Book book) {
        try {
            bookDao.delete(book, dbConnection.getConnection());
        }  catch (DBException e){
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void update(Book book) {
        try {
            bookDao.update(book, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public void cancelBook(Book book) {
        book.setAvailability(false);
        try {
            bookDao.update(book, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public void addBook(Book book) {
        book.setAvailability(true);
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            bookDao.update(book, connection);
            if (permit) {
                List<Request> requests = new ArrayList<>(requestDao.getAll(connection));
                for (Request request : requests) {
                    if (request.getBook().equals(book)) {
                        request.setStatus(false);
                        requestDao.update(request, connection);
                    }
                }
            } else {
                log.log(Level.INFO, "Automatic closing of requests is prohibited");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (DBException | SQLException e) {
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }


    @Override
    public List<Book> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        try {
            return bookDao.getUnsoldBook(date,criterion, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public String getDescription(Book book) {
        System.out.println(book.getDescription());
        return book.getDescription();
    }

    @Override
    public List<Book> getSortBooks(String criterion) {
        try {
            return bookDao.getSortBook(criterion, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }
}

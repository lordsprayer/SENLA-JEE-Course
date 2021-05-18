package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService extends ConstantUtil implements IBookService {

    private static final Logger log = LogManager.getLogger(BookService.class);
    private final IRequestDao requestDao;
    private final IBookDao bookDao;
    @Value("${number_of_months:6}")
    private Integer months;
    @Value("${permit_closing_request:true}")
    private Boolean permit;

    @Override
    public List<Book> getAll() {
        try {
            return bookDao.getAll();
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Book getById(Integer id) {
        try{
            return bookDao.getByPK(id);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void save(Book book) {
        try {
            bookDao.persist(book);
        } catch (DaoException e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Book book) {
        try {
            bookDao.delete(book);
        }  catch (DaoException e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(Book book) {
        try {
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void cancelBook(Book book) {
        book.setAvailability(false);
        try {
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void addBook(Book book) {
        book.setAvailability(true);
        try {
            bookDao.update(book);
            if (permit) {
                List<Request> requests = requestDao.getAll();
                for (Request request : requests) {
                    if (request.getBook().equals(book)) {
                        request.setStatus(false);
                        requestDao.update(request);
                    }
                }
            } else {
                log.log(Level.INFO, "Automatic closing of requests is prohibited");
            }
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }


    @Override
    public List<Book> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        try {
            return bookDao.getUnsoldBook(date,criterion);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
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
            return bookDao.getSortBook(criterion);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

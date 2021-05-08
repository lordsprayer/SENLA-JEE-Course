package com.senla.courses;

import com.senla.courses.dbdao.IBookDao;
import com.senla.courses.dbdao.IRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.service.IBookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService extends ConstantUtil implements IBookService {

    private static final Logger log = LogManager.getLogger(BookService.class);
    @Autowired
    private IRequestDao requestDao;
    @Autowired
    private IBookDao bookDao;
    @Value("${number_of_months}")
    private Integer months;
    @Value("${permit_closing_request}")
    private Boolean permit;
    @Autowired
    private HibernateUtil util;

    @Override
    public List<Book> getAll() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books = bookDao.getAll();
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Book getById(Integer id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Book book = bookDao.getByPK(id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return book;
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void save(Book book) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.persist(book);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Book book) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.delete(book);
            entityManager.getTransaction().commit();
            entityManager.close();
        }  catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(Book book) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.update(book);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void cancelBook(Book book) {
        book.setAvailability(false);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.update(book);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void addBook(Book book) {
        book.setAvailability(true);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
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
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }


    @Override
    public List<Book> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books =  bookDao.getUnsoldBook(date,criterion);
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
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
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books = bookDao.getSortBook(criterion);
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

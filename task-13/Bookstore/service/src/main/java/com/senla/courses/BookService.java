package com.senla.courses;

import com.senla.courses.api.annotation.ConfigProperty;
import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateBookDao;
import com.senla.courses.dbdao.IHibernateRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IBookService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class BookService implements IBookService {

    private static final Logger log = LogManager.getLogger(BookService.class);
    @Inject
    private IHibernateRequestDao requestDao;
    @Inject
    private IHibernateBookDao bookDao;
    @ConfigProperty(propertyName = "number_of_months")
    private Integer months;
    @ConfigProperty(propertyName = "permit_closing_request")
    private Boolean permit;
    @Inject
    private HibernateUtil util;

    @Override
    public List<Book> getAll() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books = bookDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public Book getById(Integer id) {
        EntityManager entityManager = util.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Book book = bookDao.getByPK(id, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return book;
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public void save(Book book) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.persist(book, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }

    @Override
    public void delete(Book book) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.delete(book, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        }  catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void update(Book book) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.update(book, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public void cancelBook(Book book) {
        book.setAvailability(false);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.update(book, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public void addBook(Book book) {
        book.setAvailability(true);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            bookDao.update(book, util.getEntityManager());
            if (permit) {
                List<Request> requests = requestDao.getAll(entityManager);
                for (Request request : requests) {
                    if (request.getBook().equals(book)) {
                        request.setStatus(false);
                        requestDao.update(request, util.getEntityManager());
                    }
                }
            } else {
                log.log(Level.INFO, "Automatic closing of requests is prohibited");
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }


    @Override
    public List<Book> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books =  bookDao.getUnsoldBook(date,criterion,entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Search showed no matches");
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
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Book> books = bookDao.getSortBook(criterion, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return books;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }
}

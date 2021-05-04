package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateRequestDao;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IRequestService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class RequestService implements IRequestService {

    private static final Logger log = Logger.getLogger(RequestService.class.getName());
    @Inject
    private IHibernateRequestDao requestDao;
    @Inject
    private HibernateUtil util;

    @Override
    public void createRequest(Book book) {
        if (book.getAvailability().equals(false)) {
            LocalDate date = LocalDate.now();
            Request request = new Request(book, date);
            EntityManager entityManager = util.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                requestDao.persist(request, entityManager);
                entityManager.getTransaction().commit();
                entityManager.close();
            } catch (DBException e) {
                entityManager.getTransaction().rollback();
                log.log(Level.WARNING, "Error when saving an object");
                throw new ServiceException("Error when saving an object", e);
            }
            log.log(Level.INFO, "Request created");
        } else {
            System.out.println("Запрос не может быть создан, т.к. книга всё ещё в наличии");
        }
    }

    @Override
    public void delete(Request request) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            requestDao.delete(request, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            requestDao.update(request, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public Request getById(Integer id) {
        EntityManager entityManager = util.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Request request = requestDao.getByPK(id, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return request;
        } catch (DBException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getAll() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Request> requests = requestDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return requests;
        } catch (DBException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getSortRequests() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Request> requests = requestDao.getSortRequestsByTitle(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return requests;
        } catch (DBException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<String> getSortRequestsByBookCount() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<String> list = requestDao.getSortRequestsByBookCount(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return list;
        } catch (DBException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }
}

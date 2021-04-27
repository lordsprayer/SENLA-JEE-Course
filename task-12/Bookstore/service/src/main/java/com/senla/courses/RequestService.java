package com.senla.courses;

import com.senla.courses.dbdao.IDBRequestDao;
import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IRequestService;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class RequestService implements IRequestService {

    private static final Logger log = Logger.getLogger(RequestService.class.getName());
    @Inject
    private IDBRequestDao requestDao;
    @Inject
    private DBConnection dbConnection;

    @Override
    public void createRequest(Book book) {
        if (book.getAvailability().equals(false)) {
            LocalDate date = LocalDate.now();
            Request request = new Request(book, date);
            try {
                requestDao.persist(request, dbConnection.getConnection());
            } catch (DBException e) {
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
        try {
            requestDao.delete(request, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        try {
            requestDao.update(request, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public Request getById(Integer id) {
        try{
            return requestDao.getByPK(id, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getAll() {
        try {
            return requestDao.getAll(dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getSortRequests() {
        try {
            return requestDao.getSortRequestsByTitle(dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<String> getSortRequestsByBookCount() {
        try {
            return requestDao.getSortRequestsByBookCount(dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }
}
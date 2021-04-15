package com.senla.courses.service;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.courses.api.dbdao.IDBRequestDao;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

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
            requestDao.persist(request, dbConnection.getConnection());
            System.out.println("Запрос создан");
        } else {
            System.out.println("Запрос не может быть создан, т.к. книга всё ещё в наличии");
        }
    }

    @Override
    public void delete(Request request) {
        requestDao.delete(request, dbConnection.getConnection());
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        requestDao.update(request, dbConnection.getConnection());
    }

    @Override
    public Request getById(Integer id) {
        try{
            return requestDao.getByPK(id, dbConnection.getConnection());
        } catch (DaoException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getAll() {
        return requestDao.getAll(dbConnection.getConnection());
    }

    @Override
    public List<Request> getSortRequests() {
        return requestDao.getSortRequestsByTitle(dbConnection.getConnection());
    }

    @Override
    public List<String> getSortRequestsByBookCount() {
        return requestDao.getSortRequestsByBookCount(dbConnection.getConnection());
    }
}

package com.senla.courses.service;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.SerializationHandler;

@Singleton
public class RequestService implements IRequestService {

    private static final Logger log = Logger.getLogger(RequestService.class.getName());
    @Inject
    private static IRequestDao requestDao;

    @Override
    public void createRequest(Book book) {
        if (book.getAvailability().equals(false)) {
            LocalDate date = LocalDate.now();
            Request request = new Request(book, date);
            requestDao.save(request);
            System.out.println("Запрос создан");
        } else {
            System.out.println("Запрос не может быть создан, т.к. книга всё ещё в наличии");
        }
    }

    @Override
    public void delete(Request request) {
        requestDao.delete(request);
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        requestDao.update(request);
    }

    @Override
    public int countRequests(Book book) {
        List<Request> requestList = new ArrayList<>(getAll());
        int count = 0;
        for(Request request : requestList){
            if(request.getBook().equals(book)){
                count++;
            }
        }
        return -count;
    }

    @Override
    public Request getById(Long id) {
        try{
            return requestDao.getById(id);
        } catch (DaoException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException ("Search showed no matches", e);
        }
    }

    @Override
    public List<Request> getAll() {
        return requestDao.getAll();
    }

    @Override
    public List<Request> getSortRequests() {
        return requestDao.getSortRequests();
    }

    @Override
    public List<Request> getSortRequestsByBookCount() {
        List<Request> requestList = new ArrayList<>(getAll());
        requestList.sort(Comparator.comparing(o -> countRequests(o.getBook())));
        return requestList;
    }

    @Override
    public void saveAll() {
        SerializationHandler.serialize(requestDao.getAll());
    }
}

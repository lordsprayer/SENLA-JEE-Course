package com.senla.courses.service;

import com.senla.courses.dao.IRequestDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService extends ConstantUtil implements IRequestService {

    private static final Logger log = LogManager.getLogger(RequestService.class.getName());
    private final IRequestDao requestDao;

    @Override
    public void createRequest(Book book) {
        if (book.getAvailability().equals(false)) {
            LocalDate date = LocalDate.now();
            Request request = new Request(book, date);
            try {
                requestDao.persist(request);
            } catch (DaoException e) {
                log.log(Level.WARN, SAVING_ERROR);
                throw e;
            }
            log.log(Level.INFO, "Request created");
        } else {
            System.out.println("Запрос не может быть создан, т.к. книга всё ещё в наличии");
        }
    }

    @Override
    public void delete(Request request) {
        try {
            requestDao.delete(request);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        try {
            requestDao.update(request);
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public Request getById(Integer id) {
        try{
            return requestDao.getByPK(id);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Request> getAll() {
        try {
            return requestDao.getAll();
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Request> getSortRequests() {
        try {
            return requestDao.getSortRequestsByTitle();
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<Request> getSortRequestsByBookCount() {
        try {
            return requestDao.getSortRequestsByBookCount();
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}

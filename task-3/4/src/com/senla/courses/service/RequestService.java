package com.senla.courses.service;

import java.util.Date;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

public class RequestService implements IRequestService {

    private final IRequestDao requestDao;
    public RequestService(IRequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public Request createRequest(Book book) {
        Date date = new Date();
        Request request = new Request(book, date);
        requestDao.save(request);
        return request;
    }

    @Override
    public Request closeRequest(Request request) {
        request.setStatus(false);
        requestDao.update(request);
        return null;
    }
}

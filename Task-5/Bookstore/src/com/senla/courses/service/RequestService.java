package com.senla.courses.service;

import java.time.LocalDate;
import java.util.*;

import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

public class RequestService implements IRequestService {

    private static IRequestDao requestDao;
    public RequestService(IRequestDao requestDao) {
        RequestService.requestDao = requestDao;
    }

    @Override
    public void createRequest(Book book) {
        LocalDate date = LocalDate.now();
        Request request = new Request(book, date);
        requestDao.save(request);
    }

    @Override
    public void closeRequest(Request request) {
        request.setStatus(false);
        requestDao.update(request);
    }

    @Override
    public int countRequests(Book book) {
        List<Request> requestList = new ArrayList<>(requestDao.getAll());
        int count = 0;
        for(Request request : requestList){
            if(request.getBook().equals(book)){
                count++;
            }
        }
        return -count;
    }

    @Override
    public List<Request> getSortRequestsByBookCount() {
        List<Request> requestList = new ArrayList<>(requestDao.getAll());
        requestList.sort(Comparator.comparing(o -> countRequests(o.getBook())));
        return requestList;
    }


}

package com.senla.courses.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

public class RequestService implements IRequestService {

    private static IRequestDao requestDao;
    public RequestService(IRequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public Request createRequest(Book book) {
        LocalDate date = LocalDate.now();
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



    /*public static Comparator<Request> CountRequestComparator = new Comparator<Request>() {
        List<Request> requestList = new ArrayList<>(requestDao.getAll());
        @Override
        public int compare(Request o1, Request o2) {
            return Collections.frequency(requestList, o1.getBook()) - Collections.frequency(requestList, o2.getBook());
        }
    };*/

    @Override
    //не закончено
    public List<Request> getSortRequestsByBookCount() {
        List<Request> requestList = new ArrayList<>(requestDao.getAll());
        requestList.sort(Comparator.comparing(o -> countRequests(o.getBook())));
        return requestList;
    }


}

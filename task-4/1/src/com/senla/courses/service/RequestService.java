package com.senla.courses.service;

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
        List<Book> bookList = new ArrayList<>();
        for (Request request : requestList){
            bookList.add(request.getBook());
        }
        bookList.sort(Comparator.comparingInt(o -> Collections.frequency(bookList, o)));
        List <Book> bookList1 = bookList.stream().distinct().collect(Collectors.toList());
        //Map<Integer, Book> map = new HashMap<>()
        return requestList;
    }


}

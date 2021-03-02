package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.util.List;

public interface IRequestService {

    void createRequest (Book book);
    void closeRequest (Request request);
    int countRequests(Book book);
    List<Request> getSortRequestsByBookCount();

}

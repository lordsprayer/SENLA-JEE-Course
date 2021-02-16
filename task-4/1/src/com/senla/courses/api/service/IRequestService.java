package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.util.List;

public interface IRequestService {

    Request createRequest (Book book);
    Request closeRequest (Request request);
    public int countRequests(Book book);
    List<Request> getSortRequestsByBookCount();

}

package com.senla.courses.service;

import com.senla.courses.Book;
import com.senla.courses.Request;

import java.util.List;

public interface IRequestService {

    void createRequest (Book book);
    void delete(Request request);
    void closeRequest (Request request);
    Request getById(Integer id);
    List<Request> getAll();
    List<Request> getSortRequests();
    List<Request> getSortRequestsByBookCount();

}

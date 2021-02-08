package com.senla.courses.api.service;

import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

public interface IRequestService {

    Request createRequest (Book book);
    Request closeRequest (Request request);
}

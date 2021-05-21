package com.senla.courses.service;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.RequestDto;

import java.util.List;

public interface IRequestService {

    void createRequest (BookDto book);
    void delete(Integer id);
    void closeRequest (RequestDto request);
    RequestDto getById(Integer id);
    List<RequestDto> getAll();
    List<RequestDto> getSortRequests();
    List<RequestDto> getSortRequestsByBookCount();

}

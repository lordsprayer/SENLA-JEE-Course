package com.senla.courses.service;

import com.senla.courses.dto.RequestDto;

import java.util.List;

public interface IRequestService {

    void createRequest (Integer bookId);
    void delete(Integer id);
    void closeRequest (Integer id);
    RequestDto getById(Integer id);
    List<RequestDto> getAll();
    List<RequestDto> getSortRequests();
    List<RequestDto> getSortRequestsByBookCount();

    List<RequestDto> getRequests(String sort);
}

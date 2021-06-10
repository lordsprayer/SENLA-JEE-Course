package com.senla.courses.service;

import com.senla.courses.dto.RequestDto;

import java.util.List;

public interface IRequestService {

    void createRequest (Integer bookId);
    void deleteRequest(Integer id);
    void closeRequest (Integer id);
    RequestDto getRequestById(Integer id);
    List<RequestDto> getAllRequests();
    List<RequestDto> getSortRequests();
    List<RequestDto> getSortRequestsByBookCount();
    List<RequestDto> getRequests(String sort);
}

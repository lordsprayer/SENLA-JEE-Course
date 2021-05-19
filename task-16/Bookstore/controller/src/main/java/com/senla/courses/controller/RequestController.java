package com.senla.courses.controller;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.mappers.RequestMapper;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Request;
import com.senla.courses.service.IBookService;
import com.senla.courses.service.ICustomerService;
import com.senla.courses.service.IOrderService;
import com.senla.courses.service.IRequestService;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("requests")
@RequiredArgsConstructor
public class RequestController {

    private static final Logger log = LogManager.getLogger(RequestController.class.getName());
    private final IBookService bookService;
    private final ICustomerService customerService;
    private final IRequestService requestService;
    private final IOrderService orderService;

    @GetMapping(value = "/find-all", produces = "application/json")
    public List<RequestDto> getAllCustomers(){
        try {
            List<Request> requests = requestService.getAll();
            return Converter.convertRequests(requests);
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    //@GetMapping


}

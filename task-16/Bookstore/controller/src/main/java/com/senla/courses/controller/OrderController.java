package com.senla.courses.controller;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.mappers.OrderMapper;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.service.IBookService;
import com.senla.courses.service.ICustomerService;
import com.senla.courses.service.IOrderService;
import com.senla.courses.service.IRequestService;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger log = LogManager.getLogger(OrderController.class.getName());
    private final IBookService bookService;
    private final ICustomerService customerService;
    private final IRequestService requestService;
    private final IOrderService orderService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        log.log(Level.INFO, "Received request: /orders");
        return ResponseEntity.ok(orderService.getAll());
    }

    //@GetMapping


}
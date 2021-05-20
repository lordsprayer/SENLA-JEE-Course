package com.senla.courses.controller;

import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Book;
import com.senla.courses.dto.BookDto;
import com.senla.courses.mappers.BookMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger log = LogManager.getLogger(BookController.class.getName());
    private final IBookService bookService;
    private final ICustomerService customerService;
    private final IRequestService requestService;
    private final IOrderService orderService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookDto>> getAllBook(){
        log.log(Level.INFO, "Received request: /books");
        return ResponseEntity.ok(bookService.getAll());
    }

    //@GetMapping


}

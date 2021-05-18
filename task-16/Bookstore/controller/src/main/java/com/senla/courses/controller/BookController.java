package com.senla.courses.controller;

import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Book;
import com.senla.courses.model.BookDto;
import com.senla.courses.model.BookMapper;
import com.senla.courses.service.IBookService;
import com.senla.courses.service.ICustomerService;
import com.senla.courses.service.IOrderService;
import com.senla.courses.service.IRequestService;
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
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger log = LogManager.getLogger(BookController.class.getName());
    private final IBookService bookService;
    private final ICustomerService customerService;
    private final IRequestService requestService;
    private final IOrderService orderService;

    @GetMapping(value = "/find-all", produces = "application/json")
    public List<BookDto> getAllBook(){
        try {
            List<Book> books = bookService.getAll();
            List<BookDto> bookDtoList = new ArrayList<BookDto>();
            for(Book book : books) {
                BookDto bookDto = BookMapper.INSTANCE.carToCarDto(book);
                bookDtoList.add(bookDto);
            }
            return bookDtoList;
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    //@GetMapping


}

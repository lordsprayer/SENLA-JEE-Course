package com.senla.courses.facade;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.ICustomerDao;
import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.dao.BookDao;
import com.senla.courses.dao.CustomerDao;
import com.senla.courses.dao.OrderDao;
import com.senla.courses.dao.RequestDao;
import com.senla.courses.model.Book;
import com.senla.courses.service.BookService;
import com.senla.courses.service.OrderService;
import com.senla.courses.service.RequestService;

import java.time.LocalDate;

public class BookstoreFacade {
    private static final IBookDao bookDao = new BookDao();
    private static final IOrderDao orderDao = new OrderDao();
    private static final IRequestDao requestDao = new RequestDao();
    private static final ICustomerDao customerDao = new CustomerDao();
    private static final IRequestService requestService = new RequestService(requestDao);
    private static final IBookService bookService = new BookService(bookDao, requestDao, requestService);
    private static final IOrderService orderService = new OrderService(orderDao, requestService);

    public void addBook(String title,
                        String author,
                        Integer publicationYear,
                        Double cost,
                        LocalDate receiptDate,
                        Boolean availability){
        Book book = new Book(title, author, publicationYear, cost, receiptDate, availability);
        //bookService.addBook(book);
        bookDao.save(book);
        System.out.println(book);
    }

}

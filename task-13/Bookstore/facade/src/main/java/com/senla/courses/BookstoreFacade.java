package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.IBookService;
import com.senla.courses.service.ICustomerService;
import com.senla.courses.service.IOrderService;
import com.senla.courses.service.IRequestService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Singleton
public class BookstoreFacade {
    private static final Logger log = LogManager.getLogger(BookstoreFacade.class.getName());
    @Inject
    private IBookService bookService;
    @Inject
    private ICustomerService customerService;
    @Inject
    private IRequestService requestService;
    @Inject
    private IOrderService orderService;

    public Book createBook(String title,
                        String author,
                        Integer publicationYear,
                        Double cost,
                        LocalDate receiptDate,
                        Boolean availability){
        return new Book(title, author, publicationYear, cost, receiptDate, availability);
    }

    public Customer getCustomerById(Integer id){
        try{
            return customerService.getById(id);
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void saveCustomer(Customer customer){
        try {
            customerService.save(customer);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when saving an object");
            throw e;
        }
    }

    public void deleteCustomer(Integer id){
        try {
            customerService.delete(getCustomerById(id));
        } catch(DaoException e){
            log.log(Level.WARN, "Error when deleting an object");
            throw e;
        }
    }

    public void updateCustomer(Customer customer, Integer id){
        customer.setId(id);
        try {
            customerService.update(customer);
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public List<Customer> getAllCustomers(){
        try {
            return customerService.getAll();
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public List<Customer> printAllCustomers(){
        try {
            getAllCustomers().forEach(System.out::println);
            return getAllCustomers();
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void printCustomer(Integer id){
        try {
            System.out.println(getCustomerById(id));
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void saveBook(Book book){
        try {
            bookService.save(book);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when saving an object");
            throw e;
        }
    }

    public List<Book> getAllBook(){
        try {
            return bookService.getAll();
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public Book getBookById(Integer id){
        try{
            return bookService.getById(id);
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void printAllBook(){
        try {
            getAllBook().forEach(System.out::println);
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void printBook(Integer id){
        try{
            System.out.println(getBookById(id));
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void updateBook(Book book, Integer id){
        book.setId(id);
        try {
            bookService.update(book);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when updating an object");
            throw e;
        }
    }

    public void deleteBook(Integer id){
        try {
            bookService.delete(getBookById(id));
        } catch(DaoException e){
            log.log(Level.WARN, "Error when deleting an object");
            throw e;
        }
    }

    public List<Book> sortBooks(String criterion){
        try {
            List<Book> bookList = bookService.getSortBooks(criterion);
            bookList.forEach(System.out::println);
            return bookList;
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public List<Book> sortUnsoldBooks(String criterion){
        try {
            List<Book> bookList = bookService.unsoldBook(criterion);
            bookList.forEach(System.out::println);
            return bookList;
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void addBookToWarehouse(Book book){
        try {
            bookService.addBook(book);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when updating an object");
            throw e;
        }
    }

    public void cancelBookToWarehouse(Book book){
        try {
            bookService.cancelBook(book);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when updating an object");
            throw e;
        }
    }

    public List<Book> printBooksByAvailability(Boolean availability) {
        try {
            List<Book> books = new ArrayList<>(bookService.getAll());
            List<Book> books1 = books.stream().filter(book -> book.getAvailability().equals(availability))
                    .collect(Collectors.toList());
            books1.forEach(System.out::println);
            return books1;
        } catch(DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void createOrder(Customer customer, List<Book> books) {
        try {
            orderService.createOrder(customer, books, LocalDate.now());
        } catch(DaoException e){
            log.log(Level.WARN, "Error when saving an object");
            throw e;
        }
    }

    public Customer createCustomer(String name, String surname, String phoneNumber) {
        return new Customer(name, surname, phoneNumber);
    }

    public List<Book> createBookList(List<Integer> ids) {
        try {
            List<Book> bookList = new ArrayList<>();
            for (Integer id : ids) {
                bookList.add(getBookById(id));
            }
            return bookList;
        } catch (DaoException e) {
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void setBookDescription(Book book, String description){
        book.setDescription(description);
        try{
            bookService.update(book);
        } catch(DaoException e){
            log.log(Level.WARN, "Error when updating an object");
            throw e;
        }
    }

    public String getBookDescription(Integer id){
        try {
            return bookService.getDescription(getBookById(id));
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public List<Order> printAllOrders() {
        try {
            orderService.getAll().forEach(System.out::println);
            return orderService.getAll();
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public Order getOrderById(Integer id){
        try {
            return orderService.getById(id);
        } catch (DaoException e){
            log.log(Level.WARN, "An order with this id was not found");
            throw e;
        }
    }

    public void printOrder(Integer id) {
        try {
            orderService.orderDetails(getOrderById(id));
        } catch (DaoException e){
            log.log(Level.WARN, "An order with this id was not found");
            throw e;
        }
    }

    public void deleteOrder(Integer id){
        try{
            orderService.deleteOrder(getOrderById(id));
        }  catch (DaoException e) {
            log.log(Level.WARN, "An order with this id was not found");
            throw e;
        }
    }

    public Order.Status getStatus(int severity) {
        if (severity == 1) {
            return Order.Status.NEW;
        }
        if (severity == 2) {
            return Order.Status.COMPLETED;
        }
        if (severity == 3) {
            return Order.Status.CANCELED;
        } else
            log.log(Level.WARN, "Wrong order status");
            return null;
    }


    public void changeOrderStatus(Integer id, Order.Status status){
        try {
            if (status.equals(Order.Status.COMPLETED)){
                orderService.completeOrder(getOrderById(id), LocalDate.now());
            } else {
                orderService.changeStatus(getOrderById(id), status);
            }
        } catch (DaoException e) {
            log.log(Level.WARN, "An order with this id was not found");
            throw e;
        }
    }

    public List<Order> sortOrders(String criterion){
        try {
            List<Order> orderList = orderService.getSortOrders(criterion);
            orderList.forEach(System.out::println);
            return orderList;
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public List<Order> sortCompleteOrders(String criterion,LocalDate date){
        try {
            List<Order> orderList = orderService.getSortCompletedOrders(date, criterion);
            orderList.forEach(System.out::println);
            return orderList;
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public Double countIncome(LocalDate date){
        try {
            return orderService.countIncome(date);
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public Integer countCompleteOrders(LocalDate date){
        try {
            return orderService.countCompleteOrders(date);
        } catch (DaoException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public LocalDate createDate(){
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите дату:");
            System.out.println("Введите год");
            int year = scan.nextInt();
            System.out.println("Введите месяц");
            int month = scan.nextInt();
            System.out.println("Введите день");
            int day = scan.nextInt();
            return LocalDate.of(year, month, day);
        } catch (Exception e){
            System.out.println("Данные введены неверно");
            return LocalDate.now();
        }
    }

    public void createRequest(Book book) {
        try {
            requestService.createRequest(book);
        } catch (DaoException e){
            log.log(Level.WARN, "Error when saving an object");
            throw e;
        }
    }


    public List<Request> printAllRequests(){
        try {
            requestService.getAll().forEach(System.out::println);
            return requestService.getAll();
        } catch (ServiceException e){
            log.log(Level.WARN, "Search showed no matches");
            throw e;
        }
    }

    public void deleteRequest(Integer id){
        try{
            requestService.delete(requestService.getById(id));
        } catch (DaoException e){
            log.log(Level.WARN, "An request with this id was not found");
            throw e;
        }
    }

    public void closeRequest(Integer id){
        try{
            requestService.closeRequest(requestService.getById(id));
        } catch (DaoException e){
            log.log(Level.WARN, "An request with this id was not found");
            throw e;
        }
    }

    public void sortRequestsByBookCount(){
        List<Request> requestList = requestService.getSortRequestsByBookCount();
        if(requestList.isEmpty()){
            System.out.println("Пока в базе нет запросов");
        } else {
            requestList.forEach(System.out::println);
        }
    }

    public void sortRequestsByBookTitle(){
        List<Request> requestList = requestService.getSortRequests();
        if(requestList.isEmpty()){
            System.out.println("Пока в базе нет запросов");
        } else {
            requestList.forEach(System.out::println);
        }
    }
}

package com.senla.courses.facade;

import com.senla.courses.api.service.ICustomerService;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.comparators.OrdersComparators;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Customer;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.model.Book;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class BookstoreFacade {
    private static final Logger log = Logger.getLogger(BookstoreFacade.class.getName());
    @Inject
    private ICustomerService customerService;
    @Inject
    private IRequestService requestService;
    @Inject
    private IBookService bookService;
    @Inject
    private  IOrderService orderService;

//    public void bookDB() {
//        Book book1 = new Book("Созерцатель", "Алексей Пехов", 2018, 12.5, LocalDate.of(2021, 1, 3), true);
//        Book book2 = new Book("Страж", "Алексей Пехов", 2019, 17.5, LocalDate.of(2020, 9, 21), true);
//        Book book3 = new Book("Бессмертный", "Кэтрин Валенте", 2018, 12.3, LocalDate.of(2020, 7, 28), false);
//        Customer customer1 = new Customer("Иван", "Иванов", "+375297746363");
//        Customer customer2 = new Customer("Петр",  "Петров", "+375445878745");
//        Customer customer3 = new Customer("Дмитрий", "Сидоров ", "+375443698521");
//        List<Book> books = new ArrayList<>();
//        books.add(book1);
//        books.add(book2);
//        try {
//            bookService.save(book1);
//            bookService.save(book2);
//            bookService.save(book3);
//            bookService.save(new Book("Черные крылья", "Эд Макдональд", 2018, 14.3, LocalDate.of(2020, 12, 12), false));
//            customerService.save(customer1);
//            customerService.save(customer2);
//            customerService.save(customer3);
//            orderService.createOrder(customer1, books, LocalDate.of(2020, 12, 21));
//            orderService.createOrder(customer2, books, LocalDate.of(2021, 1, 12));
//            orderService.createOrder(customer3, books, LocalDate.of(2021, 2, 3));
//        } catch (ServiceException e) {
//            log.log(Level.WARNING, "Error when saving an objects");
//            throw e;
//        }
//    }


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
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void saveCustomer(Customer customer){
        try {
            customerService.save(customer);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when saving an object");
            throw e;
        }
    }

    public void deleteCustomer(Integer id){
        try {
            customerService.delete(getCustomerById(id));
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when deleting an object");
            throw e;
        }
    }

    public void updateCustomer(Customer customer, Integer id){
        customer.setId(id);
        try {
            customerService.update(customer);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Customer> getAllCustomers(){
        try {
            return customerService.getAll();
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Customer> printAllCustomers(){
        try {
            getAllCustomers().forEach(System.out::println);
            return getAllCustomers();
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void printCustomer(Integer id){
        try {
            System.out.println(getCustomerById(id));
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void saveBook(Book book){
        try {
            bookService.save(book);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when saving an object");
            throw e;
        }
    }

    public List<Book> getAllBook(){
        try {
            return bookService.getAll();
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public Book getBookById(Integer id){
        try{
            return bookService.getById(id);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void printAllBook(){
        try {
            getAllBook().forEach(System.out::println);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void printBook(Integer id){
        try{
            System.out.println(getBookById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void updateBook(Book book, Integer id){
        book.setId(id);
        try {
            bookService.update(book);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when updating an object");
            throw e;
        }
    }

    public void deleteBook(Integer id){
        try {
            bookService.delete(getBookById(id));
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when deleting an object");
            throw e;
        }
    }

    public List<Book> sortBooks(String criterion){
        try {
            List<Book> bookList = bookService.getSortBooks(criterion);
            bookList.forEach(System.out::println);
            return bookList;
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Book> sortUnsoldBooks(String criterion){
        try {
            List<Book> bookList = bookService.unsoldBook(criterion);
            bookList.forEach(System.out::println);
            return bookList;
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void addBookToWarehouse(Book book){
        try {
            bookService.addBook(book);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when updating an object");
            throw e;
        }
    }

    public void cancelBookToWarehouse(Book book){
        try {
            bookService.cancelBook(book);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when updating an object");
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
        } catch(ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void createOrder(Customer customer, List<Book> books) {
        try {
            orderService.createOrder(customer, books, LocalDate.now());
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when saving an object");
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
        } catch (ServiceException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void setBookDescription(Book book, String description){
        book.setDescription(description);
        try{
            bookService.update(book);
        } catch(ServiceException e){
            log.log(Level.WARNING, "Error when updating an object");
            throw e;
        }
    }

    public String getBookDescription(Integer id){
        try {
            return bookService.getDescription(getBookById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Order> printAllOrders() {
        try {
            orderService.getAll().forEach(System.out::println);
            return orderService.getAll();
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public Order getOrderById(Integer id){
        try {
            return orderService.getById(id);
        } catch (ServiceException e){
            log.log(Level.WARNING, "An order with this id was not found");
            throw e;
        }
    }

    public void printOrder(Integer id) {
        try {
            orderService.orderDetails(getOrderById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "An order with this id was not found");
            throw e;
        }
    }

    public void deleteOrder(Integer id){
        try{
            orderService.deleteOrder(getOrderById(id));
        }  catch (ServiceException e) {
            log.log(Level.WARNING, "An order with this id was not found");
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
            log.log(Level.WARNING, "Wrong order status");
            throw new NullPointerException();
    }


    public void changeOrderStatus(Integer id, Order.Status status){
        try {
            if (status.equals(Order.Status.COMPLETED)){
                orderService.completeOrder(getOrderById(id), LocalDate.now());
            } else {
                orderService.changeStatus(getOrderById(id), status);
            }
        } catch (ServiceException e) {
            log.log(Level.WARNING, "An order with this id was not found");
            throw e;
        }
    }

    public List<Order> sortOrders(String criterion){
        try {
            List<Order> orderList = orderService.getSortOrders(criterion);
            orderList.forEach(System.out::println);
            return orderList;
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Order> sortCompleteOrders(String criterion,LocalDate date){
        try {
            List<Order> orderList = orderService.getSortCompletedOrders(date, criterion);
            orderList.forEach(System.out::println);
            return orderList;
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public Double countIncome(LocalDate date){
        try {
            return orderService.countIncome(date);
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public Integer countCompleteOrders(LocalDate date){
        try {
            return orderService.countCompleteOrders(date);
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
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
        } catch (ServiceException e){
            log.log(Level.WARNING, "Error when saving an object");
            throw e;
        }
    }


    public List<Request> printAllRequests(){
        try {
            requestService.getAll().forEach(System.out::println);
            return requestService.getAll();
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public void deleteRequest(Integer id){
        try{
            requestService.delete(requestService.getById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "An request with this id was not found");
            throw e;
        }
    }

    public void closeRequest(Integer id){
        try{
            requestService.closeRequest(requestService.getById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "An request with this id was not found");
            throw e;
        }
    }

    public void sortRequestsByBookCount(){
        List<String> requestList = requestService.getSortRequestsByBookCount();
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

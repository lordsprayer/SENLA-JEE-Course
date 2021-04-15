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
    @Inject
    private ICustomerService customerService;
    @Inject
    private IRequestService requestService;
    @Inject
    private IBookService bookService;
    @Inject
    private  IOrderService orderService;
    private final List<Comparator<Order>> orderComp = new ArrayList<>();
    private static final Logger log = Logger.getLogger(BookstoreFacade.class.getName());

    public  List<Comparator<Order>> createOrderComparators(){
        orderComp.add(OrdersComparators.DateComparator);
        orderComp.add(OrdersComparators.TotalCostComparator);
        orderComp.add(OrdersComparators.StatusComparator);
        return orderComp;
    }

    public void bookDB() {
        Book book1 = new Book("Созерцатель", "Алексей Пехов", 2018, 12.5, LocalDate.of(2021, 1, 3), true);
        Book book2 = new Book("Страж", "Алексей Пехов", 2019, 17.5, LocalDate.of(2020, 9, 21), true);
        Book book3 = new Book("Бессмертный", "Кэтрин Валенте", 2018, 12.3, LocalDate.of(2020, 7, 28), false);
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
        bookService.save(new Book("Черные крылья", "Эд Макдональд", 2018, 14.3, LocalDate.of(2020, 12, 12), false));
        Customer customer1 = new Customer("Иван", "Иванов", "+375297746363");
        Customer customer2 = new Customer("Петр",  "Петров", "+375445878745");
        Customer customer3 = new Customer("Дмитрий", "Сидоров ", "+375443698521");
        customerService.save(customer1);
        customerService.save(customer2);
        customerService.save(customer3);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        try {
            orderService.createOrder(customer1, books, LocalDate.of(2020, 12, 21));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            orderService.createOrder(customer2, books, LocalDate.of(2021, 1, 12));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            orderService.createOrder(customer3, books, LocalDate.of(2021, 2, 3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


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
        customerService.save(customer);
    }

    public void deleteCustomer(Integer id){
        customerService.delete(getCustomerById(id));
    }

    public void updateCustomer(Customer customer, Integer id){
        customer.setId(id);
        customerService.update(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerService.getAll();
    }

    public List<Customer> printAllCustomers(){
        getAllCustomers().forEach(System.out::println);
        return getAllCustomers();
    }

    public void printCustomer(Integer id){
        System.out.println(getCustomerById(id));
    }

    public void saveBook(Book book){
        bookService.save(book);
        //System.out.println(book);
    }

    public List<Book> getAllBook(){
        return bookService.getAll();
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
        getAllBook().forEach(System.out::println);
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
        bookService.update(book);
    }

    public void deleteBook(Integer id){
        bookService.delete(getBookById(id));
    }

    public List<Book> sortBooks(String criterion){
        List<Book> bookList = bookService.getSortBooks(criterion);
        bookList.forEach(System.out::println);
        return bookList;
    }

    public List<Book> sortUnsoldBooks(String criterion){
        List<Book> bookList = bookService.unsoldBook(criterion);
        bookList.forEach(System.out::println);
        return bookList;
    }

    public void addBookToWarehouse(Book book){
        bookService.addBook(book);
    }

    public void cancelBookToWarehouse(Book book){
        bookService.cancelBook(book);
    }

    public List<Book> printBooksByAvailability(Boolean availability) {
        List<Book> books= new ArrayList<>(bookService.getAll());
        List<Book> books1 = books.stream().filter(book -> book.getAvailability().equals(availability))
                .collect(Collectors.toList());
        books1.forEach(System.out::println);
        return books1;
    }

    public void createOrder(Customer customer, List<Book> books) throws SQLException {
        Order order = orderService.createOrder(customer, books, LocalDate.now());
        System.out.println(order);
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
        bookService.update(book);
    }

    public String getBookDescription(Integer id){
        try {
            return bookService.getDescription(getBookById(id));
        } catch (ServiceException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw e;
        }
    }

    public List<Order> printAllOrders(){
        orderService.getAll().forEach(System.out::println);
        return orderService.getAll();
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
            if(status.equals(Order.Status.COMPLETED)){
                orderService.completeOrder(getOrderById(id), LocalDate.now());
            }else {
                orderService.changeStatus(getOrderById(id), status);
            }
        } catch (ServiceException e) {
            log.log(Level.WARNING, "An order with this id was not found");
            throw e;
        }
    }

    public List<Order> sortOrders(String criterion){
        List<Order> orderList = orderService.getSortOrders(criterion);
        orderList.forEach(System.out::println);
        return orderList;
    }

    public List<Order> sortCompleteOrders(Comparator<Order> orderComparator,LocalDate date){
        List<Order> orderList = orderService.getSortCompletedOrders(orderComparator, date);
        orderList.forEach(System.out::println);
        return orderList;
    }

    public Double countIncome(LocalDate date){
        return orderService.countIncome(date);
    }

    public Integer countCompleteOrders(LocalDate date){
        return orderService.countCompleteOrders(date);
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
        requestService.createRequest(book);
    }


    public List<Request> printAllRequests(){
        requestService.getAll().forEach(System.out::println);
        return requestService.getAll();
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
        }
        else {
            requestList.forEach(System.out::println);
        }
    }

    public void sortRequestsByBookTitle(){
        List<Request> requestList = requestService.getSortRequests();
        if(requestList.isEmpty()){
            System.out.println("Пока в базе нет запросов");
        }
        else {
            requestList.forEach(System.out::println);
        }
    }
}

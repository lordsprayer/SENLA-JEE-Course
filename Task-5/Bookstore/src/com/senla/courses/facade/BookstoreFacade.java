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
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;
import com.senla.courses.service.BookService;
import com.senla.courses.service.OrderService;
import com.senla.courses.service.RequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookstoreFacade {
    private static final IBookDao bookDao = new BookDao();
    private static final IOrderDao orderDao = new OrderDao();
    private static final IRequestDao requestDao = new RequestDao();
    private static final ICustomerDao customerDao = new CustomerDao();
    private static final IRequestService requestService = new RequestService(requestDao);
    private static final IBookService bookService = new BookService(bookDao, requestDao, requestService);
    private static final IOrderService orderService = new OrderService(orderDao, requestService);
    private final List<Comparator<Book>> bookComp = new ArrayList<>();
    private final List<Comparator<Order>> orderComp = new ArrayList<>();

    public  List<Comparator<Book>> createBookComparators(){
        bookComp.add(Book.NameComparator);
        bookComp.add(Book.PublicationComparator);
        bookComp.add(Book.CostComparator);
        bookComp.add(Book.AvailabilityComparator);
        bookComp.add(Book.ReceiptComparator);
        return bookComp;
    }


    public  List<Comparator<Order>> createOrderComparators(){
        orderComp.add(Order.DateComparator);
        orderComp.add(Order.TotalCostComparator);
        orderComp.add(Order.StatusComparator);
        return orderComp;
    }

    public void bookDB() {
        Book book1 = new Book("Созерцатель", "Алексей Пехов", 2018, 12.5, LocalDate.of(2021, 1, 3), true);
        Book book2 = new Book("Страж", "Алексей Пехов", 2019, 17.5, LocalDate.of(2020, 9, 21), true);
        Book book3 = new Book("Бессмертный", "Кэтрин Валенте", 2018, 12.3, LocalDate.of(2020, 7, 28), false);
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
        bookDao.save(new Book("Черные крылья", "Эд Макдональд", 2018, 14.3, LocalDate.of(2020, 12, 12), false));
        Customer customer1 = new Customer("Иван", "Иванов", "+375297746363");
        Customer customer2 = new Customer("Петр",  "Петров", "+375445878745");
        Customer customer3 = new Customer("Дмитрий", "Сидоров ", "+375443698521");
        customerDao.save(customer1);
        customerDao.save(customer2);
        customerDao.save(customer3);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        orderService.createOrder(customer1, books, LocalDate.of(2020, 12, 21));
        orderService.createOrder(customer2, books, LocalDate.of(2021, 1, 12));
        orderService.createOrder(customer3, books, LocalDate.of(2021, 2, 3));
    }


    public Book createBook(String title,
                        String author,
                        Integer publicationYear,
                        Double cost,
                        LocalDate receiptDate,
                        Boolean availability){
        return new Book(title, author, publicationYear, cost, receiptDate, availability);
    }

    public void saveBook(Book book){
        bookDao.save(book);
        System.out.println(book);
    }

    public List<Book> getAllBook(){
        return bookDao.getAll();
    }

    public Book getBookById(Long id){
        return bookDao.getById(id);
    }

    public void printAllBook(){
        getAllBook().forEach(System.out::println);
    }

    public void printBook(Long id){
        System.out.println(getBookById(id));
    }

    public void updateBook(Book book, Long id){
        book.setId(id);
        bookDao.update(book);
    }

    public void deleteBook(Long id){
        bookDao.delete(getBookById(id));
    }

    public void sortBooks(Comparator<Book> bookComparator){
        List<Book> bookList = bookDao.getSortBooks(bookComparator);
        bookList.forEach(System.out::println);
    }

    public void sortUnsoldBooks(Comparator<Book> bookComparator){
        List<Book> bookList = bookService.unsoldBook();
        bookList.sort(bookComparator);
        bookList.forEach(System.out::println);
    }

    public void addBookToWarehouse(Book book){
        bookService.addBook(book);
    }

    public void cancelBookToWarehouse(Book book){
        bookService.cancelBook(book);
    }

    public void printBooksByAvailability(Boolean availability) {
        List<Book> books= new ArrayList<>(bookDao.getAll());
        books.stream().filter(book -> book.getAvailability().equals(availability))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    public void createOrder(Customer customer, List<Book> books) {
        Order order = orderService.createOrder(customer, books, LocalDate.now());
        System.out.println(order);
    }

    public Customer createCustomer(String name, String surname, String phoneNumber) {
        Customer customer = new Customer(name, surname, phoneNumber);
        customerDao.save(customer);
        return customer;
    }

    public List<Book> createBookList(List<Long> ids){
        List<Book> bookList = new ArrayList<>();
        for(Long id : ids ){
            bookList.add(getBookById(id));
        }
        return bookList;
    }

    public void setBookDescription(Book book, String description){
        book.setDescription(description);
        bookDao.update(book);
    }

    public void getBookDescription(Long id){
        bookService.lookDescription(getBookById(id));
    }

    public void printAllOrders(){
        orderDao.getAll().forEach(System.out::println);
    }

    public Order getOrderById(Long id){
        return orderDao.getById(id);
    }

    public void printOrder(Long id) {
        orderService.orderDetails(getOrderById(id));
    }

    public void deleteOrder(Long id){
        orderService.cancelOrder(getOrderById(id));
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
            return null;
    }


    public void changeOrderStatus(Long id, Order.Status status){
        if(status.equals(Order.Status.COMPLETED)){
            orderService.completeOrder(getOrderById(id), LocalDate.now());
        }else {
            orderService.changeStatus(getOrderById(id), status);
        }
    }

    public void sortOrders(Comparator<Order> orderComparator){
        List<Order> orderList = orderDao.getSortOrders(orderComparator);
        orderList.forEach(System.out::println);
    }

    public void sortCompleteOrders(Comparator<Order> orderComparator,LocalDate date){
        List<Order> orderList = orderService.getSortCompletedOrders(orderComparator, date);
        orderList.forEach(System.out::println);
    }

    public Double countIncome(LocalDate date){
        return orderService.countIncome(date);
    }

    public Integer countCompleteOrders(LocalDate date){
        return orderService.countCompleteOrders(date);
    }

    public LocalDate createDate(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите дату:");
        System.out.println("Введите год");
        int year = scan.nextInt();
        System.out.println("Введите месяц");
        int month = scan.nextInt();
        System.out.println("Введите день");
        int day = scan.nextInt();
        return LocalDate.of(year, month,day);
    }

    public void createRequest(Book book){
        requestService.createRequest(book);
    }

    public void printAllRequests(){
        requestDao.getAll().forEach(System.out::println);
    }

    public void deleteRequest(Long id){
        requestDao.delete(requestDao.getById(id));
    }

    public void closeRequest(Long id){
        requestService.closeRequest(requestDao.getById(id));
    }

    public void sortRequestsByBookCount(){
        List<Request> requestList = requestService.getSortRequestsByBookCount();
        requestList.forEach(System.out::println);
    }

    public void sortRequestsByBookTitle(){
        List<Request> requestList = requestDao.getSortRequests();
        requestList.forEach(System.out::println);
    }
}

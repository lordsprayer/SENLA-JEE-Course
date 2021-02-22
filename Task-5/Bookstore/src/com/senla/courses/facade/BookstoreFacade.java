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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookstoreFacade {
    private static final IBookDao bookDao = new BookDao();
    private static final IOrderDao orderDao = new OrderDao();
    private static final IRequestDao requestDao = new RequestDao();
    private static final ICustomerDao customerDao = new CustomerDao();
    private static final IRequestService requestService = new RequestService(requestDao);
    private static final IBookService bookService = new BookService(bookDao, requestDao, requestService);
    private static final IOrderService orderService = new OrderService(orderDao, requestService);
    private List<Comparator<Book>> bookComp = new ArrayList<Comparator<Book>>();

    public  List<Comparator<Book>> createBookComparators(){
        bookComp.add(Book.NameComparator);
        bookComp.add(Book.PublicationComparator);
        bookComp.add(Book.CostComparator);
        bookComp.add(Book.AvailabilityComparator);
        bookComp.add(Book.ReceiptComparator);
        return bookComp;
    }

    public void bookDB() {
        Book book1 = new Book("Созерцатель", "Алексей Пехов", 2018, 12.5, LocalDate.of(2021, 1, 3), true);
        Book book2 = new Book("Страж", "Алексей Пехов", 2019, 17.5, LocalDate.of(2020, 9, 21), true);
        Book book3 = new Book("Бессмертный", "Кэтрин Валенте", 2018, 12.3, LocalDate.of(2020, 7, 28), true);
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
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

    public void printAllBook(){
        getAllBook().forEach(System.out::println);
    }

    public void printBook(Long id){
        System.out.println(getBookById(id));
    }

    public Book getBookById(Long id){
        return bookDao.getById(id);
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
        books.stream().filter(book -> book.getAvailability().equals(availability)).collect(Collectors.toList()).forEach(System.out::println);
    }


}

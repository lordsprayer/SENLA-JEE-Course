import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.api.dao.IOrderDao;
import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.api.service.IBookService;
import com.senla.courses.api.service.IOrderService;
import com.senla.courses.api.service.IRequestService;
import com.senla.courses.dao.BookDao;
import com.senla.courses.dao.OrderDao;
import com.senla.courses.dao.RequestDao;
import com.senla.courses.model.Book;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;
import com.senla.courses.service.BookService;
import com.senla.courses.service.OrderService;
import com.senla.courses.service.RequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookstore {

    private static final IBookDao bookDao = new BookDao();
    private static final IOrderDao orderDao = new OrderDao();
    private static final IRequestDao requestDao = new RequestDao();
    private static final IRequestService requestService = new RequestService(requestDao);
    private static final IBookService bookService = new BookService(bookDao, requestDao, requestService);
    private static final IOrderService orderService = new OrderService(orderDao, requestDao, requestService);

    public static void main(String[] args) {
        Book book1 = new Book ("Созерцатель","Алексей Пехов", 2018,12.5, LocalDate.of(2021, 1, 3),true);
        Book book2 = new Book ("Страж","Алексей Пехов", 2019,17.5, LocalDate.of(2020, 9, 21),true);
        bookDao.save(book1);
        //System.out.println(bookDao.getById(1L));
        bookDao.save(book2);
        bookService.cancelBook(book2);
        List <Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Order order = orderService.createOrder(books, LocalDate.of(2020, 1, 21));
        //System.out.println(order);
        //System.out.println(requestDao.getById(1L));
        //orderService.cancelOrder(order);
        Date date = new Date();
        Request request = new Request(book2, date);
        requestDao.save(request);
        //System.out.println(request);
        Order order1 = orderService.createOrder(books, LocalDate.of(2020, 1, 21));
        orderService.changeStatus(order1, Order.Status.COMPLETED);
        //bookService.addBook(book2);
        //System.out.println(order1);
        //System.out.println(request);
        Book book3 = new Book ("Бессмертный","Кэтрин Валенте",  2018,12.3, LocalDate.of(2020, 7, 28),true);
        bookDao.save(book3);
        books.add(book3);
        Order order3 = orderService.createOrder(books, LocalDate.of(2020, 1, 21));
        List<Book> bookList = bookDao.getSortBooks(Book.ReceiptComparator);
        bookList.forEach(System.out::println);
        bookDao.getAll().forEach(System.out::println);

        /*System.out.println("Без сортировки:\n");

        for(Book book : bookList){
            System.out.println(book);
        }
        //сортировка книг по названию
        bookList.sort(Book.NameComparator);
        System.out.println("Сортировка по названию книги:\n");
        for(Book book : bookList){
            System.out.println(book);
        }
        //сортировка книг по году издания
        bookList.sort(Book.PublicationComparator);
        System.out.println("Сортировка по году издания книги:\n");
        for(Book book : bookList){
            System.out.println(book);
        }
        System.out.println();
        //сортировка книг по стоимости
        bookList.sort(Book.CostComparator);
        for(Book book : bookList){
            System.out.println(book);
        }
        //сортировка книг по наличию (сначала книги в наличии)
        bookList.sort(Book.AvailabilityComparator);
        System.out.println("Сортировка по наличию книги:\n");

        for(Book book : bookList){
            System.out.println(book);
        }*/
        /*//сортировка заказов по дате выполнения
        List<Order> orders = new ArrayList<>(orderDao.getAll());
        orders.sort(Order.DateComparator);
        for(Order ord : orders){
                System.out.println(ord);
        }
        //сортировка заказов по стоимости
        //List<Order> orders = new ArrayList<>();
        //orders.addAll(orderDao.getAll());
        orders.sort(Order.TotalCostComparator);
        for(Order ord : orders){
            System.out.println(ord);
        }*/
        List<Order> orderList = orderDao.getSortOrders(Order.StatusComparator);
        orderList.forEach(System.out::println);
        System.out.println();
        List<Order> orderList1 = orderDao.getSortCompletedOrders(Order.TotalCostComparator);
        orderList1.forEach(System.out::println);
        //сортировка запросов по названию книги
        Request request1 = new Request(book3, date);
        requestDao.save(request1);
        Request request2 = new Request(book3, date);
        Request request3 = new Request(book1, date);
        Request request4 = new Request(book3, date);
        Request request5 = new Request(book3, date);
        Request request6 = new Request(book1, date);
        requestDao.save(request2);
        requestDao.save(request3);
        requestDao.save(request4);
        requestDao.save(request5);
        requestDao.save(request6);
        List<Request> requests = requestService.getSortRequestsByBookCount();

        requests.forEach(System.out::println);

        //сортировка выполненных заказов по дате выполнения
        //List<Order> orders = new ArrayList<>();
        //orders.addAll(orderDao.getAll());
        /*orders.sort(Order.DateComparator);
        for(Order ord : orders){
            if(ord.getStatus() == Order.Status.COMPLETED){
                System.out.println(ord);
            }
        }
*/

    }

}

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

import java.util.Date;

public class Bookstore {

    private static final IBookDao bookDao = new BookDao();
    private static final IOrderDao orderDao = new OrderDao();
    private static final IRequestDao requestDao = new RequestDao();
    private static final IRequestService requestService = new RequestService(requestDao);
    private static final IBookService bookService = new BookService(bookDao, requestDao, requestService);
    private static final IOrderService orderService = new OrderService(orderDao, requestDao, requestService);

    public static void main(String[] args) {
        Book book1 = new Book ("Созерцатель","Алексей Пехов", 12.5, true);
        Book book2 = new Book ("Страж","Алексей Пехов", 17.5, true);
        bookDao.save(book1);
        System.out.println(bookDao.getById(1L));
        bookDao.save(book2);
        bookService.cancelBook(book2);
        Order order = orderService.createOrder(book1, book2);
        System.out.println(order);
        System.out.println(requestDao.getById(1L));
        orderService.cancelOrder(order);
        Date date = new Date();
        Request request = new Request(book2, date);
        requestDao.save(request);
        System.out.println(request);
        Order order1 = orderService.createOrder(book1, book2);
        orderService.changeStatus(order1, Order.Status.COMPLETED);
        bookService.addBook(book2);
        System.out.println(order1);
        System.out.println(request);

    }

}

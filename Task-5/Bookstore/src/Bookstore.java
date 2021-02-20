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
import com.senla.courses.ui.menu.MenuController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {



    public static void main(String[] args) {
        /*Book book1 = new Book ("Созерцатель","Алексей Пехов", 2018,12.5, LocalDate.of(2021, 1, 3),true);
        Book book2 = new Book ("Страж","Алексей Пехов", 2019,17.5, LocalDate.of(2020, 9, 21),true);
        Book book3 = new Book ("Бессмертный","Кэтрин Валенте",  2018,12.3, LocalDate.of(2020, 7, 28),true);
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
        Customer customer1 = new Customer("Иванов Иван", "+375297746363");
        Customer customer2 = new Customer("Петров Петр", "+375445878745");
        Customer customer3 = new Customer("Сидоров Дмитрий", "+375443698521");
        customerDao.save(customer1);
        customerDao.save(customer2);
        customerDao.save(customer3);
        requestService.createRequest(book3);
        requestService.createRequest(book3);
        requestService.createRequest(book1);
        requestService.createRequest(book3);
        requestService.createRequest(book2);
        requestService.createRequest(book1);
        bookService.cancelBook(book2);
        List <Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Order order = orderService.createOrder(customer1, books, LocalDate.of(2020, 1, 21));
        LocalDate date = LocalDate.now();
        Request request = new Request(book2, date);
        requestDao.save(request);
        Order order1 = orderService.createOrder(customer2, books, LocalDate.of(2020, 1, 21));
        orderService.changeStatus(order1, Order.Status.COMPLETED);
        books.add(book3);
        book3.setDescription("«Бессмертный» — история Марьи Моревны и Кощея Бессмертного в России ХХ века.");
        bookDao.update(book3);
        Order order3 = orderService.createOrder(customer3, books, LocalDate.of(2020, 1, 21));
        System.out.println("Sorting books by date of receipt");
        List<Book> bookList1 = bookDao.getSortBooks(Book.ReceiptComparator);
        bookList1.forEach(System.out::println);
        System.out.println("Sorting books by title");
        List<Book> bookList2 = bookDao.getSortBooks(Book.NameComparator);
        bookList2.forEach(System.out::println);
        System.out.println("Sorting books by cost");
        List<Book> bookList3 = bookDao.getSortBooks(Book.CostComparator);
        bookList3.forEach(System.out::println);
        System.out.println("Sorting books by avaibality");
        List<Book> bookList4 = bookDao.getSortBooks(Book.AvailabilityComparator);
        bookList4.forEach(System.out::println);
        System.out.println("Sorting books by year of publication");
        List<Book> bookList = bookDao.getSortBooks(Book.PublicationComparator);
        bookList.forEach(System.out::println);
        //bookDao.getAll().forEach(System.out::println);
        System.out.println("Sorting orders by date of execution");
        List<Order> orderList = orderDao.getSortOrders(Order.DateComparator);
        orderList.forEach(System.out::println);
        System.out.println();
        System.out.println("Sorting orders by total cost");
        List<Order> orderLis1 = orderDao.getSortOrders(Order.TotalCostComparator);
        orderLis1.forEach(System.out::println);
        System.out.println();
        System.out.println("Sorting orders by status");
        List<Order> orderLis2 = orderDao.getSortOrders(Order.StatusComparator);
        orderLis2.forEach(System.out::println);
        System.out.println();
        System.out.println("Sorting comleted orders by total cost");
        List<Order> orderList1 = orderDao.getSortCompletedOrders(Order.TotalCostComparator);
        orderList1.forEach(System.out::println);
        System.out.println("Sorting requests by book count");
        List<Request> requests = requestService.getSortRequestsByBookCount();
        requests.forEach(System.out::println);
        System.out.println("Sorting requests by book title");
        List<Request> requests1 = requestDao.getSortRequests();
        requests1.forEach(System.out::println);
        System.out.println("The amount of money earned since 10.11.2020 " + orderService.countIncome(LocalDate.of(2020, 11, 10)));
        System.out.println("Number of completed orders since 10.11.2020 " + orderService.countIncome(LocalDate.of(2020, 11, 10)));
        System.out.println("list of books unsold for more than 6 months " + bookService.unsoldBook());
        System.out.println("Description of the book \"Бессмертный\"");
        bookService.lookDescription(book3);*/
        MenuController.getInstance().run();

    }

}

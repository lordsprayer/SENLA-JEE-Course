import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.ICustomerDao;
import com.senla.courses.dao.IOrderDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;
import com.senla.courses.service.OrderService;
import com.senla.courses.util.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private final Customer customerOne = new Customer("Alex", "Tikhonov", "+375297769755");
    private final Customer customerTwo = new Customer("Alex", "Maleachi", "+375448575896");
    private final Book bookOne = new Book("Война и мир", "Лев Толстой", 2002, 12.8, LocalDate.of(2020, 12, 12), true);
    private final Book bookTwo = new Book("Ревизор", "Николай Гоголь", 2012, 15.7, LocalDate.of(2021, 1, 23), true);
    private final Book bookThree = new Book("Преступление и наказание", "Фёдор Достоевский", 2021, 11.4, LocalDate.of(2021, 4, 7), false);
    private final List<Book> bookList = new ArrayList<>(List.of(bookOne, bookTwo, bookThree));
    private final Request requestOne = new Request(bookThree, LocalDate.now());
    private final Order orderOne = new Order(customerOne, bookList, LocalDate.of(2021, 5, 7));
    private final Order orderTwo = new Order(customerTwo, List.of(bookThree), LocalDate.of(2021, 6, 1));
    private final Order orderThree = new Order(customerOne, bookList, LocalDate.now());


    @Mock
    private IBookDao bookDao;
    @Mock
    private IOrderDao orderDao;
    @Mock
    private IRequestDao requestDao;
    @Mock
    private ICustomerDao customerDao;
    @InjectMocks
    private OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getOrderByIdTest() {
        Double totalCost = Calculator.calculateTotalCost(bookList);
        orderOne.setId(1);

        when(orderDao.getByPK(1)).thenReturn(orderOne);

        OrderDto orderDto = orderService.getOrderById(1);

        assertEquals(1, orderDto.getId());
        assertEquals(Mappers.getMapper(CustomerMapper.class).customerToCustomerDto(customerOne), orderDto.getCustomer());
        assertEquals(Mappers.getMapper(BookMapper.class).bookListToBookDtoList(bookList), orderDto.getBooks());
        assertEquals(LocalDate.of(2021,5,7), orderDto.getCreationDate());
        assertEquals(LocalDate.of(1970,1,1), orderDto.getCompletionDate());
        assertEquals(totalCost, orderDto.getTotalCost());
        assertEquals("NEW", orderDto.getStatus());
    }

    @Test
    public void getAllOrdersTest() {
        List<Order> list = new ArrayList<>();
        list.add(orderOne);
        list.add(orderTwo);

        when(orderDao.getAll()).thenReturn(list);

        List<OrderDto> empList = orderService.getAllOrders();

        assertEquals(2, empList.size());
        verify(orderDao, times(1)).getAll();
    }

    @Test
    public void createOrderTest() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));

        when(customerDao.getByPK(1)).thenReturn(customerOne);
        when(bookDao.getByPK(1)).thenReturn(bookOne);
        when(bookDao.getByPK(2)).thenReturn(bookTwo);
        when(bookDao.getByPK(3)).thenReturn(bookThree);

        orderService.createOrder(1, list);

        verify(orderDao, times(1)).persist(orderThree);
        verify(requestDao, times(1)).persist(requestOne);
        verify(bookDao, times(1)).getByPK(1);
        verify(bookDao, times(1)).getByPK(2);
        verify(bookDao, times(1)).getByPK(3);
        verify(customerDao, times(1)).getByPK(1);
    }

    @Test
    public void deleteOrderTest() {
        when(orderDao.getByPK(1)).thenReturn(orderOne);

        orderService.deleteOrder(1);

        verify(orderDao, times(1)).delete(orderOne);
    }

    @Test
    public void changeStatusTest_ifStatusCompleted() {
        when(orderDao.getByPK(1)).thenReturn(orderOne);

        orderService.changeStatus(1, "COMPLETED");

        verify(orderDao, times(1)).update(orderOne);
        assertEquals(LocalDate.now(), orderOne.getCompletionDate());
    }

    @Test
    public void changeStatusTest_ifStatusCanceled() {
        when(orderDao.getByPK(1)).thenReturn(orderOne);

        orderService.changeStatus(1, "CANCELED");

        verify(orderDao, times(1)).update(orderOne);
        assertEquals(LocalDate.of(1970, 1, 1), orderOne.getCompletionDate());
    }

    @Test
    public void countCompleteOrdersTest() {
        orderOne.setStatus(Order.Status.COMPLETED);
        orderOne.setCompletionDate(LocalDate.now());
        orderTwo.setStatus(Order.Status.COMPLETED);
        orderTwo.setCompletionDate(LocalDate.now());
        List<Order> list = new ArrayList<>();
        list.add(orderOne);
        list.add(orderTwo);
        list.add(orderThree);

        when(orderDao.getAll()).thenReturn(list);

        Integer count = orderService.countCompleteOrders(LocalDate.now().minusDays(2));

        assertEquals(2, count);

    }
}

import com.senla.courses.dao.ICustomerDao;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Customer;
import com.senla.courses.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private final Customer customerOne = new Customer("Alex", "Tikhonov", "+375297769755");
    private final Customer customerTwo = new Customer("Alex", "Maleachi", "+375448575896");
    private final Customer customerThree = new Customer("Steve", "Waugh", "+375332584758");
    private final CustomerDto customerDto = new CustomerDto("Alex", "Tikhonov", "+375297769755");

    @Mock
    private ICustomerDao customerDao;
    @InjectMocks
    private CustomerService customerService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void getCustomerByIdTest() {
        when(customerDao.getByPK(1)).thenReturn(customerOne);

        CustomerDto customerDto = customerService.getCustomerById(1);
        assertEquals("Alex", customerDto.getName());
        assertEquals("Tikhonov", customerDto.getSurname());
        assertEquals("+375297769755", customerDto.getPhone());
    }

    @Test
    public void createCustomerTest() {
        customerService.createCustomer(customerDto);

        verify(customerDao, times(1)).persist(Mappers.getMapper(CustomerMapper.class).customerDtoToCustomer(customerDto));
    }

    @Test
    public void getAllCustomersTest() {
        List<Customer> list = new ArrayList<>();

        list.add(customerOne);
        list.add(customerTwo);
        list.add(customerThree);

        when(customerDao.getAll()).thenReturn(list);

        List<CustomerDto> empList = customerService.getAllCustomers();

        assertEquals(3, empList.size());
        verify(customerDao, times(1)).getAll();
    }

    @Test
    public void deleteCustomerTest() {
        when(customerDao.getByPK(1)).thenReturn(customerOne);

        customerService.deleteCustomer(1);

        verify(customerDao, times(1)).delete(customerOne);
    }

    @Test
    public void updateCustomerTest() {
        when(customerDao.getByPK(null)).thenReturn(customerTwo);

        customerService.updateCustomer(customerDto);

        verify(customerDao, times(1)).update(Mappers.getMapper(CustomerMapper.class).customerDtoToCustomer(customerDto));

    }

}


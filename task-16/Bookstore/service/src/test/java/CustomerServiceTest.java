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

    @Mock
    private ICustomerDao customerDao;
    @InjectMocks
    private CustomerService customerService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void findCustomerByIdTest() {
        when(customerDao.getByPK(1)).thenReturn(new Customer("Alex", "Tikhonov", "+375297769755"));

        CustomerDto customerDto = customerService.getById(1);
        assertEquals("Alex", customerDto.getName());
        assertEquals("Tikhonov", customerDto.getSurname());
        assertEquals("+375297769755", customerDto.getPhone());
    }

    @Test
    public void createCustomerTest() {
        CustomerDto customerDto = new CustomerDto("Alex", "Tikhonov", "+375297769755");

        customerService.save(customerDto);

        verify(customerDao, times(1)).persist(Mappers.getMapper(CustomerMapper.class).customerDtoToCustomer(customerDto));
    }

    @Test
    public void getAllCustomersTest() {
        List<Customer> list = new ArrayList<>();
        Customer empOne = new Customer("John", "John", "+375295857845");
        Customer empTwo = new Customer("Alex", "Maleachi", "+375448575896");
        Customer empThree = new Customer("Steve", "Waugh", "+375332584758");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(customerDao.getAll()).thenReturn(list);

        List<CustomerDto> empList = customerService.getAll();

        assertEquals(3, empList.size());
        verify(customerDao, times(1)).getAll();
    }

    @Test
    public void deleteCustomerTest() {
        Customer customer = new Customer("Alex", "Tikhonov", "+375297769755");

        when(customerDao.getByPK(1)).thenReturn(customer);

        customerService.delete(1);

        verify(customerDao, times(1)).delete(customer);
    }
}


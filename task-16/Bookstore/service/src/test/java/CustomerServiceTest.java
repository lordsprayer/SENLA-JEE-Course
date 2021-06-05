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
    public void findCustomerById() {
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
}


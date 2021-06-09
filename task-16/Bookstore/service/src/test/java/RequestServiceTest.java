import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.service.RequestService;
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
public class RequestServiceTest {

    private final Book bookOne = new Book("Война и мир", "Лев Толстой", 2002, 12.8, LocalDate.of(2020, 12, 12), false);
    private final Book bookTwo = new Book("Ревизор", "Николай Гоголь", 2012, 15.7, LocalDate.of(2021, 1, 23), true);
    private final Request requestOne = new Request(bookOne, LocalDate.of(2020,12,12));
    private final Request requestTwo = new Request(bookTwo, LocalDate.of(2021, 5, 7));
    private final Request requestThree = new Request(bookOne, LocalDate.of(2021, 6, 1));

    @Mock
    private IRequestDao requestDao;
    @Mock
    private IBookDao bookDao;
    @InjectMocks
    private RequestService requestService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRequestByIdTest() {
        when(requestDao.getByPK(1)).thenReturn(requestOne);

        RequestDto requestDto = requestService.getRequestById(1);
        assertEquals(Mappers.getMapper(BookMapper.class).bookToBookDto(bookOne), requestDto.getBook());
        assertEquals(LocalDate.of(2020,12,12), requestDto.getDate());
        assertEquals(true, requestDto.getStatus());
    }

    @Test
    public void getAllRequestsTest() {
        List<Request> list = new ArrayList<>();
        list.add(requestOne);
        list.add(requestTwo);
        list.add(requestThree);

        when(requestDao.getAll()).thenReturn(list);

        List<RequestDto> empList = requestService.getAllRequests();

        assertEquals(3, empList.size());
        verify(requestDao, times(1)).getAll();
    }

    @Test
    public void createRequestTest_ifBookAvailabilityFalse() {
        when(bookDao.getByPK(1)).thenReturn(bookOne);

        requestService.createRequest(1);
        Request request = new Request(bookOne, LocalDate.now());

        verify(requestDao, times(1)).persist(request);
    }

    @Test
    public void createRequestTest_ifBookAvailabilityTrue() {
        when(bookDao.getByPK(1)).thenReturn(bookTwo);

        requestService.createRequest(1);
        Request request = new Request(bookTwo, LocalDate.now());

        verify(requestDao, times(0)).persist(request);
    }

    @Test
    public void deleteRequestTest() {
        when(requestDao.getByPK(1)).thenReturn(requestOne);

        requestService.deleteRequest(1);

        verify(requestDao, times(1)).delete(requestOne);
    }

    @Test
    public void closeRequestTest() {
        when(requestDao.getByPK(1)).thenReturn(requestOne);

        requestService.closeRequest(1);

        verify(requestDao, times(1)).update(requestOne);
    }

}

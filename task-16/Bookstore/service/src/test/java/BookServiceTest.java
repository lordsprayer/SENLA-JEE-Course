import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.BookDto;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.service.BookService;
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
public class BookServiceTest {

    private final Book bookOne = new Book("Война и мир", "Лев Толстой", 2002, 12.8, LocalDate.of(2020, 12, 12), true);
    private final Book bookTwo = new Book("Ревизор", "Николай Гоголь", 2012, 15.7, LocalDate.of(2021, 1, 23), true);
    private final Book bookThree = new Book("Преступление и наказание", "Фёдор Достоевский", 2021, 11.4, LocalDate.of(2021, 4, 7), true);
    private final BookDto bookDto = new BookDto("Ревизор", "Николай Гоголь", 2012, 15.7, LocalDate.of(2021, 1, 23), true);

    @Mock
    private IBookDao bookDao;
    @Mock
    private IRequestDao requestDao;
    @InjectMocks
    private BookService bookService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBookByIdTest() {
        when(bookDao.getByPK(1)).thenReturn(bookOne);

        BookDto bookDto = bookService.getBookById(1);
        assertEquals("Война и мир", bookDto.getTitle());
        assertEquals("Лев Толстой", bookDto.getAuthor());
        assertEquals(2002, bookDto.getPublicationYear());
        assertEquals(12.8, bookDto.getCost());
        assertEquals(LocalDate.of(2020, 12, 12), bookDto.getReceiptDate());
        assertEquals(true, bookDto.getAvailability());
    }

    @Test
    public void getAllBooksTest() {
        List<Book> list = new ArrayList<>();
        list.add(bookOne);
        list.add(bookTwo);
        list.add(bookThree);

        when(bookDao.getAll()).thenReturn(list);

        List<BookDto> empList = bookService.getAllBooks();

        assertEquals(3, empList.size());
        verify(bookDao, times(1)).getAll();
    }

    @Test
    public void createBookTest() {

        bookService.createBook(bookDto);

        verify(bookDao, times(1)).persist(Mappers.getMapper(BookMapper.class).bookDtoToBook(bookDto));
    }

    @Test
    public void deleteBookTest() {

        when(bookDao.getByPK(1)).thenReturn(bookOne);

        bookService.deleteBook(1);

        verify(bookDao, times(1)).delete(bookOne);
    }

    @Test
    public void updateBookTest() {

        when(bookDao.getByPK(null)).thenReturn(bookOne);

        bookService.updateBook(bookDto);

        verify(bookDao, times(1)).update(Mappers.getMapper(BookMapper.class).bookDtoToBook(bookDto));

    }


    @Test
    public void cancelBookTest() {
        when(bookDao.getByPK(1)).thenReturn(bookOne);

        bookService.cancelBook(1);

        verify(bookDao, times(1)).update(bookOne);

    }

    @Test
    public void addBookTest_ifPermitTrue() {
        List<Request> list = new ArrayList<>();
        bookOne.setAvailability(false);
        bookTwo.setAvailability(false);
        Request requestOne = new Request(bookOne, LocalDate.now());
        requestOne.setId(1);
        Request requestTwo = new Request(bookTwo, LocalDate.now().minusDays(5));
        requestTwo.setId(2);
        Request requestThree = new Request(bookOne, LocalDate.now().minusDays(8));
        requestThree.setId(3);

        list.add(requestOne);
        list.add(requestTwo);
        list.add(requestThree);

        when(bookDao.getByPK(1)).thenReturn(bookOne);
        when(requestDao.getAll()).thenReturn(list);

        bookService.setPermit(true);
        bookService.addBook(1);

        verify(bookDao, times(1)).update(bookOne);
        verify(requestDao, times(1)).update(requestOne);
        verify(requestDao, times(0)).update(requestTwo);
        verify(requestDao, times(1)).update(requestThree);
    }

    @Test
    public void addBookTest_ifPermitFalse() {
        List<Request> list = new ArrayList<>();
        bookOne.setAvailability(false);
        bookTwo.setAvailability(false);
        Request requestOne = new Request(bookOne, LocalDate.now());
        Request requestTwo = new Request(bookTwo, LocalDate.now());
        Request requestThree = new Request(bookOne, LocalDate.now());

        list.add(requestOne);
        list.add(requestTwo);
        list.add(requestThree);

        when(bookDao.getByPK(1)).thenReturn(bookOne);
        when(requestDao.getAll()).thenReturn(list);

        bookService.setPermit(false);
        bookService.addBook(1);

        verify(bookDao, times(1)).update(bookOne);
        verify(requestDao, times(0)).update(requestOne);
        verify(requestDao, times(0)).update(requestTwo);
        verify(requestDao, times(0)).update(requestThree);
    }
}

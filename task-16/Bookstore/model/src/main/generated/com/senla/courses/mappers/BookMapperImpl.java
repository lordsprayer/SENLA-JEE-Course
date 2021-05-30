package com.senla.courses.mappers;

import com.senla.courses.dto.BookDto;
import com.senla.courses.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-30T15:03:51+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setCost( book.getCost() );
        bookDto.setId( book.getId() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setAuthor( book.getAuthor() );
        bookDto.setPublicationYear( book.getPublicationYear() );
        bookDto.setAvailability( book.getAvailability() );
        bookDto.setReceiptDate( book.getReceiptDate() );
        bookDto.setDescription( book.getDescription() );

        return bookDto;
    }

    @Override
    public Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setCost( bookDto.getCost() );
        if ( bookDto.getId() != null ) {
            book.setId( bookDto.getId() );
        }
        book.setTitle( bookDto.getTitle() );
        book.setAuthor( bookDto.getAuthor() );
        book.setPublicationYear( bookDto.getPublicationYear() );
        book.setReceiptDate( bookDto.getReceiptDate() );
        book.setAvailability( bookDto.getAvailability() );
        book.setDescription( bookDto.getDescription() );

        return book;
    }

    @Override
    public List<BookDto> bookListToBookDtoList(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( bookToBookDto( book ) );
        }

        return list;
    }
}

package com.senla.courses.mappers;

import com.senla.courses.dto.BookDto;
import com.senla.courses.model.Book;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T14:13:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        Double cost = null;
        Integer id = null;
        String title = null;
        String author = null;
        Integer publicationYear = null;
        Boolean availability = null;
        LocalDate receiptDate = null;
        String description = null;

        cost = book.getCost();
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        publicationYear = book.getPublicationYear();
        availability = book.getAvailability();
        receiptDate = book.getReceiptDate();
        description = book.getDescription();

        BookDto bookDto = new BookDto( id, title, author, publicationYear, availability, cost, receiptDate, description );

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
}

package com.senla.courses.model;

import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-18T16:41:50+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto carToCarDto(Book book) {
        if ( book == null ) {
            return null;
        }

        Double costOfBook = null;
        Integer id = null;
        String title = null;
        String author = null;
        Integer publicationYear = null;
        LocalDate receiptDate = null;
        String description = null;

        costOfBook = book.getCost();
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        publicationYear = book.getPublicationYear();
        receiptDate = book.getReceiptDate();
        description = book.getDescription();

        BookDto bookDto = new BookDto( id, title, author, publicationYear, costOfBook, receiptDate, description );

        return bookDto;
    }
}

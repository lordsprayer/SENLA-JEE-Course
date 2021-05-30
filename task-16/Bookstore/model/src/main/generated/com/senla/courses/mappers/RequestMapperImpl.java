package com.senla.courses.mappers;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import java.time.LocalDate;
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
public class RequestMapperImpl implements RequestMapper {

    @Override
    public RequestDto requestToRequestDto(Request request) {
        if ( request == null ) {
            return null;
        }

        Book book = null;
        Integer id = null;
        LocalDate date = null;
        Boolean status = null;

        book = request.getBook();
        id = request.getId();
        date = request.getDate();
        status = request.getStatus();

        RequestDto requestDto = new RequestDto( id, book, date, status );

        return requestDto;
    }

    @Override
    public Request requestDtoToRequest(RequestDto request) {
        if ( request == null ) {
            return null;
        }

        Request request1 = new Request();

        request1.setBook( bookDtoToBook( request.getBook() ) );
        if ( request.getId() != null ) {
            request1.setId( request.getId() );
        }
        request1.setDate( request.getDate() );
        request1.setStatus( request.getStatus() );

        return request1;
    }

    @Override
    public List<RequestDto> requestListToRequestDtoList(List<Request> requests) {
        if ( requests == null ) {
            return null;
        }

        List<RequestDto> list = new ArrayList<RequestDto>( requests.size() );
        for ( Request request : requests ) {
            list.add( requestToRequestDto( request ) );
        }

        return list;
    }

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        if ( bookDto.getId() != null ) {
            book.setId( bookDto.getId() );
        }
        book.setTitle( bookDto.getTitle() );
        book.setAuthor( bookDto.getAuthor() );
        book.setPublicationYear( bookDto.getPublicationYear() );
        book.setCost( bookDto.getCost() );
        book.setReceiptDate( bookDto.getReceiptDate() );
        book.setAvailability( bookDto.getAvailability() );
        book.setDescription( bookDto.getDescription() );

        return book;
    }
}

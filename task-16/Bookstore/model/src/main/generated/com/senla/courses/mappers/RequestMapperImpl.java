package com.senla.courses.mappers;

import com.senla.courses.dto.RequestDto;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-19T12:26:33+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
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
}

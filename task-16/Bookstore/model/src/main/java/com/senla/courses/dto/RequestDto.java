package com.senla.courses.dto;

import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import lombok.Data;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Data
public class RequestDto {
    private Integer id;
    private BookDto book;
    private LocalDate date;
    private Boolean status;

    public RequestDto(Integer id, Book book, LocalDate date, Boolean status) {
        this.id = id;
        this.book = Mappers.getMapper(BookMapper.class).bookToBookDto(book);
        this.date = date;
        this.status = status;
    }
}

package com.senla.courses.dto;

import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDto {
    private Integer id;
    private BookDto book;
    private LocalDate date;
    private Boolean status;

    public void setBook(Book book) {
        this.book = BookMapper.INSTANCE.bookToBookDto(book);
    }

    public RequestDto(Integer id, Book book, LocalDate date, Boolean status) {
        this.id = id;
        this.book = BookMapper.INSTANCE.bookToBookDto(book);
        this.date = date;
        this.status = status;
    }
}

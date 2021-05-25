package com.senla.courses.mappers;

import com.senla.courses.dto.BookDto;
import com.senla.courses.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "cost")
    BookDto bookToBookDto(Book book);

    @Mapping(target = "cost")
    Book bookDtoToBook(BookDto bookDto);

    @Mapping(target = "cost")
    List<BookDto> bookListToBookDtoList(List<Book> books);
}

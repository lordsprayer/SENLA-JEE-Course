package com.senla.courses.mappers;

import com.senla.courses.model.Book;
import com.senla.courses.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    @Mapping(target = "cost")
    BookDto bookToBookDto(Book book);
}

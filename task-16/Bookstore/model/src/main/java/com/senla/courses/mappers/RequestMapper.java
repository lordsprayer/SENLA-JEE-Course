package com.senla.courses.mappers;

import com.senla.courses.dto.RequestDto;
import com.senla.courses.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "book")
    RequestDto requestToRequestDto(Request request);
}

package com.senla.courses.mappers;

import com.senla.courses.dto.RequestDto;
import com.senla.courses.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "book")
    RequestDto requestToRequestDto(Request request);

    @Mapping(target = "book")
    Request requestDtoToRequest(RequestDto request);

    @Mapping(target = "book")
    List<RequestDto> requestListToRequestDtoList(List<Request> requests);
}

package ru.anikitin.codereview.task02.controller.mapper;

import org.mapstruct.Mapper;
import ru.anikitin.codereview.task02.controller.Request;
import ru.anikitin.codereview.task02.controller.Response;
import ru.anikitin.codereview.task02.controller.dto.RequestDto;
import ru.anikitin.codereview.task02.controller.dto.ResponseDto;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    Request map(RequestDto requestDto);

    ResponseDto map(Response response);
}

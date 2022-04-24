package ru.anikitin.codereview.task02.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.anikitin.codereview.task02.controller.dto.StatResponseDto;
import ru.anikitin.codereview.task02.domain.Link;

@Mapper(componentModel = "spring")
public interface LinkStatMapper {

    @Mapping(target = "rank", source = "linkStat.rank")
    @Mapping(target = "count", source = "linkStat.requestCount")
    @Mapping(target = "link", source = "shortLink")
    @Mapping(target = "original", source = "originalLink")
    StatResponseDto map(Link link);
}

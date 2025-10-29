package com.astrazeneca.ci.mapper;

import com.astrazeneca.ci.dto.request.CreateCompetitorRequest;
import com.astrazeneca.ci.dto.response.CreateCompetitorResponse;
import com.astrazeneca.ci.model.Competitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetitorMapper {

    CompetitorMapper COMPETITOR_MAPPER = Mappers.getMapper(CompetitorMapper.class);

    @Mapping(target = "d", ignore = true)
    Competitor toEntity(CreateCompetitorRequest request);

    CreateCompetitorResponse toCreatedDto(Competitor competitor);
}

package com.astrazeneca.ci.mapper;

import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.dto.response.CreateInsightResponse;
import com.astrazeneca.ci.dto.response.InsightResponse;
import com.astrazeneca.ci.model.Competitor;
import com.astrazeneca.ci.model.Insight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InsightMapper {

    InsightMapper INSIGHT_MAPPER = Mappers.getMapper(InsightMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "competitor", source = "competitor")
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    Insight toEntity(CreateInsightRequest request, Competitor competitor);

    CreateInsightResponse toCreatedDto(Insight insight);

    @Mapping(target = "competitor", source = "insight.competitor.name")
    InsightResponse toDto(Insight insight);
}

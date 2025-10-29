package com.astrazeneca.ci.dto.response;

public record CreateCompetitorResponse(
        Long id,
        String name,
        String therapeuticArea,
        String country
) {
}

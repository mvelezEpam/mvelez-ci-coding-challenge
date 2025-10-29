package com.astrazeneca.ci.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateCompetitorRequest(
        @NotBlank
        String name,
        @NotBlank
        String therapeuticArea,
        @Nullable
        String country
) {
}

package com.astrazeneca.ci.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CreateCompetitorRequest(
        @NotBlank
        String name,
        @NotBlank
        String therapeuticArea,
        @Nullable
        String country
) {
}

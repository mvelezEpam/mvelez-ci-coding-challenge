package com.astrazeneca.ci.dto.request;

import com.astrazeneca.ci.model.Category;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateInsightRequest(
        @NotNull
        Long competitorId,
        @NotBlank
        String title,
        @NotNull
        Category category,
        @Nullable
        String detail,
        @NotBlank
        String region,
        @NotBlank
        String source,
        @Digits(integer = 1, fraction = 0)
        @Min(1)
        @Max(5)
        Integer confidence
) {
}

package com.astrazeneca.ci.dto.response;

import com.astrazeneca.ci.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record CreateInsightResponse(
        Long id,
        CreateCompetitorResponse competitor,
        String title,
        Category category,
        String detail,
        String region,
        String source,
        Integer confidence,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant createdAt
) {
}

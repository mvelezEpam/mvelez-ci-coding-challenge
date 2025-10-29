package com.astrazeneca.ci.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record InsightResponse(
        Long id,
        String competitor,
        String title,
        String category,
        String region,
        Integer confidence,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant createdAt
) {
}

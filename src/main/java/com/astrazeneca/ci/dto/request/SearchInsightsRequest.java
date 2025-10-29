package com.astrazeneca.ci.dto.request;

import com.astrazeneca.ci.model.Category;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SearchInsightsRequest(
        String competitorName,
        String region,
        Category category,
        Instant from,
        Instant to
) {
}

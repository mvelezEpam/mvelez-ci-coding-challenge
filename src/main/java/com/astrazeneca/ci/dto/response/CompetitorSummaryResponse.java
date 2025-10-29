package com.astrazeneca.ci.dto.response;

import com.astrazeneca.ci.model.Category;

import java.util.HashMap;
import java.util.Map;

public record CompetitorSummaryResponse(String competitor, Long totalInsights, Map<Category, Long> byCategory,
                                        Double avgConfidence) {

    public CompetitorSummaryResponse(String competitor, Long totalInsights, Double avgConfidence) {
        this(competitor, totalInsights, new HashMap<>(), avgConfidence);
    }
}

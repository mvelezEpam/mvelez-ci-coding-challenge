package com.astrazeneca.ci.util;

import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.model.Competitor;

public class TestUtil {

    public static CreateInsightRequest buildInsightRequest(Long competitorId, int confidence, Category category) {
        return CreateInsightRequest.builder()
                .title("Pfizer launches new lung cancer therapy")
                .competitorId(competitorId)
                .category(category)
                .detail("...")
                .region("US")
                .source("Evaluate Pharma")
                .confidence(confidence)
                .build();
    }

    public static Competitor buildCompetitor(String name) {
        return new Competitor(name, "Oncology", "US");
    }
}

package com.astrazeneca.ci.controller;

import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.dto.response.CreateInsightResponse;
import com.astrazeneca.ci.dto.response.InsightResponse;
import com.astrazeneca.ci.dto.response.PageResponse;
import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.service.InsightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/insights")
@RequiredArgsConstructor
@Validated
public class InsightController {

    private final InsightService insightService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public CreateInsightResponse createInsight(@Valid @RequestBody CreateInsightRequest request) {
        return insightService.createInsight(request);
    }

    @GetMapping
    public PageResponse<InsightResponse> getInsights(
            @RequestParam(required = false) String competitorName,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return PageResponse.of(insightService.getInsights(competitorName, region, category, from, to, pageable));
    }
}

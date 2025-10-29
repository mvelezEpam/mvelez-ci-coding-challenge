package com.astrazeneca.ci.controller;

import com.astrazeneca.ci.dto.request.CreateCompetitorRequest;
import com.astrazeneca.ci.dto.response.CompetitorSummaryResponse;
import com.astrazeneca.ci.dto.response.CreateCompetitorResponse;
import com.astrazeneca.ci.service.CompetitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/competitors")
@RequiredArgsConstructor
@Validated
public class CompetitorController {

    private final CompetitorService competitorService;

    @PostMapping
    public CreateCompetitorResponse createCompetitor(@Valid @RequestBody CreateCompetitorRequest request) {
        return competitorService.createCompetitor(request);
    }

    @GetMapping("{id}/summary")
    public CompetitorSummaryResponse getCompetitorSummary(@PathVariable("id") Long competitorId) {
        return competitorService.getCompetitorSummary(competitorId);
    }
}

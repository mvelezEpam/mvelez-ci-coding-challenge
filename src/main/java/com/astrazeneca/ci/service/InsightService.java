package com.astrazeneca.ci.service;

import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.dto.response.CreateInsightResponse;
import com.astrazeneca.ci.dto.response.InsightResponse;
import com.astrazeneca.ci.exception.NotFoundException;
import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.model.Competitor;
import com.astrazeneca.ci.model.Insight;
import com.astrazeneca.ci.repository.InsightRepository;
import com.astrazeneca.ci.repository.InsightSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.astrazeneca.ci.mapper.InsightMapper.INSIGHT_MAPPER;

@Service
@RequiredArgsConstructor
public class InsightService {

    private final InsightRepository insightRepository;
    private final CompetitorService competitorService;

    @Transactional
    public CreateInsightResponse createInsight(CreateInsightRequest request) {
        Competitor competitor = competitorService.getCompetitorById(request.competitorId())
                .orElseThrow(()
                        -> new NotFoundException(String.format("Competitor with ID %s does not exist", request.competitorId())));

        Insight insight = INSIGHT_MAPPER.toEntity(request, competitor);
        Insight created = insightRepository.save(insight);
        return INSIGHT_MAPPER.toCreatedDto(created);
    }

    public Page<InsightResponse> getInsights(String competitorName, String region, Category category, Instant from,
                                             Instant to, Pageable pageable) {
        Specification<Insight> spec = null;
        if (competitorName != null && !competitorName.isBlank()) {
            spec = InsightSpecification.competitorContains(competitorName);
        }

        if (region != null && !region.isBlank()) {
            spec = (spec == null
                    ? InsightSpecification.regionContains(region)
                    : spec.and(InsightSpecification.regionContains(region)));
        }

        if (category != null) {
            spec = (spec == null
                    ? InsightSpecification.categoryIs(category)
                    : spec.and(InsightSpecification.categoryIs(category)));
        }

        if (from != null) {
            spec = (spec == null
                    ? InsightSpecification.createdAtFrom(from)
                    : spec.and(InsightSpecification.createdAtFrom(to)));
        }

        if (to != null) {
            spec = (spec == null
                    ? InsightSpecification.createdAtTo(to)
                    : spec.and(InsightSpecification.createdAtTo(to)));
        }

        if (spec == null) {
            spec = (root, query, cb) -> cb.conjunction();
        }

        return insightRepository.findAll(spec, pageable)
                .map(INSIGHT_MAPPER::toDto);
    }
}

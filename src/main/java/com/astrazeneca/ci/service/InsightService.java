package com.astrazeneca.ci.service;

import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.dto.response.CreateInsightResponse;
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
                        -> new RuntimeException(String.format("Competitor with ID %s does not exist", request.competitorId())));

        Insight insight = INSIGHT_MAPPER.toEntity(request, competitor);
        Insight created = insightRepository.save(insight);
        return INSIGHT_MAPPER.toCreatedDto(created);
    }

    public Page<CreateInsightResponse> getInsights(String competitorName, String region, Category category, Pageable pageable) {
        Specification<Insight> spec = null;
        if (competitorName != null && !competitorName.isBlank()) {
            spec = (spec == null ?
                    InsightSpecification.competitorContains(competitorName)
                    : spec.and(InsightSpecification.competitorContains(competitorName)));
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

        if (spec == null) {
            spec = (root, query, cb) -> cb.conjunction();
        }

        return insightRepository.findAll(spec, pageable)
                .map(INSIGHT_MAPPER::toCreatedDto);
    }
}

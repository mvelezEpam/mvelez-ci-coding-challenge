package com.astrazeneca.ci.service;

import com.astrazeneca.ci.dto.request.CreateCompetitorRequest;
import com.astrazeneca.ci.dto.response.CompetitorSummaryResponse;
import com.astrazeneca.ci.dto.response.CreateCompetitorResponse;
import com.astrazeneca.ci.exception.NotFoundException;
import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.model.Competitor;
import com.astrazeneca.ci.repository.CompetitorRepository;
import com.astrazeneca.ci.repository.CompetitorSummaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.astrazeneca.ci.mapper.CompetitorMapper.COMPETITOR_MAPPER;

@Service
@RequiredArgsConstructor
public class CompetitorService {

    private final CompetitorRepository competitorRepository;
    private final CompetitorSummaryRepository competitorSummaryRepository;

    @Transactional
    public CreateCompetitorResponse createCompetitor(CreateCompetitorRequest request) {
        Competitor created = competitorRepository.save(COMPETITOR_MAPPER.toEntity(request));
        return COMPETITOR_MAPPER.toCreatedDto(created);
    }

    public Optional<Competitor> getCompetitorById(Long competitorId) {
        return competitorRepository.findById(competitorId);
    }

    public CompetitorSummaryResponse getCompetitorSummary(Long competitorId) {
        CompetitorSummaryResponse summaryResponse = competitorSummaryRepository.findCompetitorSummary(competitorId)
                .orElseThrow(()
                        -> new NotFoundException(String.format("Competitor with ID %s does not exist", competitorId)));

        List<Object[]> categoryCounts = competitorSummaryRepository.findCategoryCountsByCompetitorId(competitorId);

        for (Object[] row : categoryCounts) {
            Category category = (Category) row[0];
            Long count = (Long) row[1];
            summaryResponse.byCategory().put(category, count);
        }

        return summaryResponse;
    }
}

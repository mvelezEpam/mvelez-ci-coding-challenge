package com.astrazeneca.ci.service;

import com.astrazeneca.ci.dto.request.CreateCompetitorRequest;
import com.astrazeneca.ci.dto.response.CreateCompetitorResponse;
import com.astrazeneca.ci.model.Competitor;
import com.astrazeneca.ci.repository.CompetitorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.astrazeneca.ci.mapper.CompetitorMapper.COMPETITOR_MAPPER;

@Service
@RequiredArgsConstructor
public class CompetitorService {

    private final CompetitorRepository competitorRepository;

    @Transactional
    public CreateCompetitorResponse createCompetitor(CreateCompetitorRequest request) {
        Competitor created = competitorRepository.save(COMPETITOR_MAPPER.toEntity(request));
        return COMPETITOR_MAPPER.toCreatedDto(created);
    }

    public Optional<Competitor> getCompetitorById(Long competitorId) {
        return competitorRepository.findById(competitorId);
    }
}

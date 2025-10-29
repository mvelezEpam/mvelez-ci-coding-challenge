package com.astrazeneca.ci.service;


import com.astrazeneca.ci.dto.request.CreateInsightRequest;
import com.astrazeneca.ci.dto.response.CreateInsightResponse;
import com.astrazeneca.ci.exception.NotFoundException;
import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.model.Competitor;
import com.astrazeneca.ci.model.Insight;
import com.astrazeneca.ci.repository.CompetitorRepository;
import com.astrazeneca.ci.repository.InsightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.astrazeneca.ci.util.TestUtil.buildCompetitor;
import static com.astrazeneca.ci.util.TestUtil.buildInsightRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class InsightServiceTest {

    @Autowired
    InsightService insightService;

    @Autowired
    InsightRepository insightRepository;

    @Autowired
    CompetitorRepository competitorRepository;

    @BeforeEach
    void setup() {
        insightRepository.deleteAll();
        competitorRepository.deleteAll();
    }

    @Test
    void testSaveAndRetrieveInsight() {
        // Arrange
        Competitor competitor = buildCompetitor("Pfizer");
        Competitor savedCompetitor = competitorRepository.save(competitor);

        CreateInsightRequest insightRequest = buildInsightRequest(savedCompetitor.getId(), 5, Category.PRODUCT_LAUNCH);

        // Act
        CreateInsightResponse savedInsight = insightService.createInsight(insightRequest);
        Optional<Insight> retrievedInsight = insightRepository.findById(savedInsight.id());

        // Assert
        assertThat(retrievedInsight).isPresent();
        assertThat(retrievedInsight.get().getId()).isEqualTo(savedInsight.id());
        assertThat(retrievedInsight.get().getCategory()).isEqualTo(Category.PRODUCT_LAUNCH);
    }

    @Test
    void testInstanceFailsToCreateIfCompetitorNotFound() {
        // Arrange
        Long invalidCompetitor = 2L;
        CreateInsightRequest insightRequest = buildInsightRequest(invalidCompetitor, 3, Category.PRODUCT_LAUNCH);

        // Act and Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> insightService.createInsight(insightRequest)
        );
        assertThat(exception.getMessage()).isEqualTo(String.format("Competitor with ID %s does not exist", invalidCompetitor));
    }
}

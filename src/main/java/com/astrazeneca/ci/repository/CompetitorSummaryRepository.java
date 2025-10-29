package com.astrazeneca.ci.repository;

import com.astrazeneca.ci.dto.response.CompetitorSummaryResponse;
import com.astrazeneca.ci.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitorSummaryRepository extends JpaRepository<Competitor, Long> {

    @Query("""
        SELECT new com.astrazeneca.ci.dto.response.CompetitorSummaryResponse(
            c.name, COUNT(i), AVG(i.confidence)
        )
        FROM Insight i
        JOIN i.competitor c
        WHERE c.id = :competitorId
        GROUP BY c.name
    """)
    Optional<CompetitorSummaryResponse> findCompetitorSummary(@Param("competitorId") Long competitorId);

    @Query("""
        SELECT i.category, COUNT(i)
        FROM Insight i
        WHERE i.competitor.id = :competitorId
        GROUP BY i.category
    """)
    List<Object[]> findCategoryCountsByCompetitorId(@Param("competitorId") Long competitorId);
}

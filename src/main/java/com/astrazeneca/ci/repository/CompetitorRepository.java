package com.astrazeneca.ci.repository;

import com.astrazeneca.ci.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
}

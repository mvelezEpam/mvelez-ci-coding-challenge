package com.astrazeneca.ci.repository;

import com.astrazeneca.ci.model.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InsightRepository extends JpaRepository<Insight, Long>, JpaSpecificationExecutor<Insight> {

}

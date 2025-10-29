package com.astrazeneca.ci.repository;

import com.astrazeneca.ci.model.Category;
import com.astrazeneca.ci.model.Insight;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class InsightSpecification {

    public static Specification<Insight> competitorContains(String competitorName) {
        return specificationContains("competitor.name", competitorName);
    }

    public static Specification<Insight> regionContains(String region) {
        return specificationContains("region", region);
    }

    public static Specification<Insight> categoryIs(Category category) {
        return (root, query, cb) -> {
            if (category == null) return cb.conjunction(); // ignore if null
            return cb.equal(root.get("category"), category);
        };
    }

    public static Specification<Insight> createdAtFrom(Instant from) {
        return (root, query, cb) -> {
            if (from == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get("createdAt"), from);
        };
    }

    public static Specification<Insight> createdAtTo(Instant to) {
        return (root, query, cb) -> {
            if (to == null) return cb.conjunction();
            return cb.lessThanOrEqualTo(root.get("createdAt"), to);
        };
    }

    private static Specification<Insight> specificationContains(String fieldName, String fieldValue) {
        return (root, query, cb) ->
                fieldValue == null ? null : cb.equal(root.get(fieldName), fieldValue);
    }
}

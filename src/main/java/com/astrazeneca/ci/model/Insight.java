package com.astrazeneca.ci.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Data
@Table(name = "insights")
public class Insight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competitor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competitor competitor;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private Category category;

    @Column(name = "detail")
    private String detail;

    @Column(name = "source")
    private String source;

    @Column(name = "region")
    private String region;

    @Column(name = "confidence")
    private Integer confidence;

    @Column(name = "created_at")
    private Instant createdAt;
}

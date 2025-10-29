package com.astrazeneca.ci.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competitors")
public class Competitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "therapeutic_area")
    private String therapeuticArea;

    @Column(name = "country")
    private String country;

    public Competitor(String name, String therapeuticArea, String country) {
        this.name = name;
        this.therapeuticArea = therapeuticArea;
        this.country = country;
    }
}

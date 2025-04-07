package com.example.GymWise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "gymwise-exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("muscleGroup")
    private String muscleGroup;

    @Column(nullable = false)
    @JsonProperty("subMuscleGroup")
    private String subMuscleGroup;

    @JsonProperty("difficulty")
    @Column(precision = 3, scale = 2)
    private BigDecimal difficulty;

    @JsonProperty("effectiveness")
    @Column(precision = 3, scale = 2)
    private BigDecimal effectiveness;

    @JsonProperty("videoUrl")
    private String videoUrl;

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", subMuscleGroup='" + subMuscleGroup + '\'' +
                ", difficulty=" + difficulty +
                ", effectiveness=" + effectiveness +
                '}';
    }

}

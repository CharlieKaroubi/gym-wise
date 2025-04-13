package com.example.GymWise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(name = "muscle_group", nullable = false)
    private String muscleGroup;

    @Column(name = "sub_muscle_group", nullable = false)
    private String subMuscleGroup;

    @JsonProperty("difficulty")
    @Column(precision = 3, scale = 2)
    private BigDecimal difficulty;

    @JsonProperty("effectiveness")
    @Column(precision = 3, scale = 2)
    private BigDecimal effectiveness;

    @Column(name = "video_url")
    private String videoUrl;

    public Exercise(String name, String muscleGroup, String subMuscleGroup, BigDecimal difficulty, BigDecimal effectiveness, String videoUrl) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.subMuscleGroup = subMuscleGroup;
        this.difficulty = difficulty;
        this.effectiveness = effectiveness;
        this.videoUrl = videoUrl;
    }

    public Exercise() {}

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

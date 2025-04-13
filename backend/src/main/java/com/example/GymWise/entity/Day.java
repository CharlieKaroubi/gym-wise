package com.example.GymWise.entity;

import com.example.GymWise.converter.ExerciseMapListConverter;
import com.example.GymWise.entity.day_exercise.DayExercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayExercise> exercises = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "split_id")
    private Split split;

    public Day(String title, List<DayExercise> exercises) {
        this.title = title;
        this.exercises = exercises;
    }

    public Day() {}
}

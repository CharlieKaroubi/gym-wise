package com.example.GymWise.entity.day_exercise;

import com.example.GymWise.entity.Day;
import com.example.GymWise.entity.Exercise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "day_exercises")
public class DayExercise {

    @EmbeddedId
    private DayExerciseId id = new DayExerciseId();

    @ManyToOne
    @MapsId("dayId")
    private Day day;

    @ManyToOne
    @MapsId("exerciseId")
    private Exercise exercise;

    @Column(name = "sets_reps")
    private String setsReps;

    public DayExercise() {}

    public DayExercise(Day day, Exercise exercise, String setsReps) {
        this.day = day;
        this.exercise = exercise;
        this.setsReps = setsReps;
    }

}

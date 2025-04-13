package com.example.GymWise.entity.day_exercise;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DayExerciseId implements Serializable {

    private Long dayId;
    private Long exerciseId;

    public DayExerciseId() {
    }

    public DayExerciseId(Long dayId, Long exerciseId) {
        this.dayId = dayId;
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayExerciseId that)) return false;
        return Objects.equals(dayId, that.dayId) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayId, exerciseId);
    }
}


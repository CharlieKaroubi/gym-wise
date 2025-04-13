package com.example.GymWise.dto;

import com.example.GymWise.entity.Exercise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayExerciseDto {
    private String exerciseName;
    private String setsReps;
}

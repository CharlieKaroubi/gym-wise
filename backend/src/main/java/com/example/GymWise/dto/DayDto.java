package com.example.GymWise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayDto {
    private String title;
    private List<DayExerciseDto> exercises;
}

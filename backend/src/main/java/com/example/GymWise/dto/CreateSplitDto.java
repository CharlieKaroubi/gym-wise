package com.example.GymWise.dto;

import com.example.GymWise.entity.Day;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateSplitDto {
    private String name;
    private String description;
    private String concentration;
    private List<DayDto> days;
}

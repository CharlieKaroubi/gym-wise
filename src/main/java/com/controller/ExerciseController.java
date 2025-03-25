package com.controller;
import com.entity.Exercise;
import com.entity.muscle.*;
import com.service.ExerciseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    //Get all exercises
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

}

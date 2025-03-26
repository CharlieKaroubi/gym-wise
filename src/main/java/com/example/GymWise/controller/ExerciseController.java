package com.example.GymWise.controller;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/group/{muscleGroup}")
    public List<Exercise> getByMuscleGroup(@PathVariable String muscleGroup) {
        return exerciseService.getByMuscleGroup(muscleGroup);
    }

    @GetMapping("/subgroup/{subMuscleGroup}")
    public List<Exercise> getBySubMuscleGroup(@PathVariable String subMuscleGroup) {
        return exerciseService.getBySubMuscleGroup(subMuscleGroup);
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        Exercise created = exerciseService.addExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}

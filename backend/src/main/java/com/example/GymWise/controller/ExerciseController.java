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

    @GetMapping("/group/{name}")
    public Exercise getByName(@PathVariable String name) {
        return exerciseService.getByName(name);
    }


    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        Exercise created = exerciseService.addExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExercise(@RequestParam String name) {
        exerciseService.removeExerciseByName(name);
        return ResponseEntity.noContent().build();
    }

}

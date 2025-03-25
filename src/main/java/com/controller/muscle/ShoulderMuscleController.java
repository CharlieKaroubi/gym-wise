package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.ShoulderMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/shoulder")
public class ShoulderMuscleController {

    @Autowired
    private ShoulderMuscleService shoulderMuscleService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllShoulderMuscles() {
        List<Exercise> exercises = shoulderMuscleService.getAllShoulderExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/front")
    public ResponseEntity<List<Exercise>> getFrontDeltExercises() {
        List<Exercise> exercises = shoulderMuscleService.getFrontDeltExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/side")
    public ResponseEntity<List<Exercise>> getSideDeltExercises() {
        List<Exercise> exercises = shoulderMuscleService.getSideDeltExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/rear")
    public ResponseEntity<List<Exercise>> getRearDeltExercises() {
        List<Exercise> exercises = shoulderMuscleService.getRearDeltExercises();
        return ResponseEntity.ok(exercises);
    }

    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

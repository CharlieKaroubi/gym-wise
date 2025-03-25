package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.ArmMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/arms")
public class ArmMuscleController {

    @Autowired
    private ArmMuscleService armMuscleService;

    // Get all arm exercises
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllArmExercises() {
        List<Exercise> exercises = armMuscleService.getAllArmExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/bicep")
    public ResponseEntity<List<Exercise>> getBicepExercises() {
        List<Exercise> exercises = armMuscleService.getBicepExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/tricep")
    public ResponseEntity<List<Exercise>> getTricepExercises() {
        List<Exercise> exercises = armMuscleService.getTricepExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/forearm")
    public ResponseEntity<List<Exercise>> getForearmExercises() {
        List<Exercise> exercises = armMuscleService.getForearmExercises();
        return ResponseEntity.ok(exercises);
    }

    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

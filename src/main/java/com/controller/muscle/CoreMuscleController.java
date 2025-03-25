package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.CoreMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/back")
public class CoreMuscleController {

    @Autowired
    private CoreMuscleService coreMuscleService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllCoreExercises() {
        List<Exercise> exercises = coreMuscleService.getAllCoreExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/upper")
    public ResponseEntity<List<Exercise>> getUpperAbsExercises() {
        List<Exercise> exercises = coreMuscleService.getUpperAbsExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/lower")
    public ResponseEntity<List<Exercise>> getLowerAbsExercises() {
        List<Exercise> exercises = coreMuscleService.getLowerAbsExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/obliques")
    public ResponseEntity<List<Exercise>> getObliquesExercises() {
        List<Exercise> exercises = coreMuscleService.getObliquesExercises();
        return ResponseEntity.ok(exercises);
    }

    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

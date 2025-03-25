package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.ChestMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/chest")
public class ChestMuscleController {

    @Autowired
    private ChestMuscleService chestMuscleService;

    // Get all chest exercises
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllChestExercises() {
        List<Exercise> exercises = chestMuscleService.getAllChestExercises();
        return ResponseEntity.ok(exercises);
    }

    // Get upper chest exercises
    @GetMapping("/upper")
    public ResponseEntity<List<Exercise>> getUpperChestExercises() {
        List<Exercise> exercises = chestMuscleService.getUpperChestExercises();
        return ResponseEntity.ok(exercises);
    }

    //Get lower chest exercises
    @GetMapping("/lower")
    public ResponseEntity<List<Exercise>> getLowerChestExercises() {
        List<Exercise> exercises = chestMuscleService.getLowerChestExercises();
        return ResponseEntity.ok(exercises);
    }


    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

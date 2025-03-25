package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.LegMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/leg")
public class LegMuscleController {

    @Autowired
    private LegMuscleService legMuscleService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllLegExercises() {
        List<Exercise> exercises = legMuscleService.getAllLegExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/quad")
    public ResponseEntity<List<Exercise>> getQuadExercises() {
        List<Exercise> exercises = legMuscleService.getQuadExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/hamstring")
    public ResponseEntity<List<Exercise>> getHamstringExercises() {
        List<Exercise> exercises = legMuscleService.getHamstringExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/adductor")
    public ResponseEntity<List<Exercise>> getAdductorExercises() {
        List<Exercise> exercises = legMuscleService.getAdductorExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/calf")
    public ResponseEntity<List<Exercise>> getCalfExercises() {
        List<Exercise> exercises = legMuscleService.getCalfExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/glute")
    public ResponseEntity<List<Exercise>> getGluteExercises() {
        List<Exercise> exercises = legMuscleService.getGluteExercises();
        return ResponseEntity.ok(exercises);
    }

    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

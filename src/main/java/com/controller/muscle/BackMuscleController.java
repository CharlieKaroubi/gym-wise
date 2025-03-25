package com.controller.muscle;
import com.entity.Exercise;
import com.service.muscle.BackMuscleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/exercises/back")
public class BackMuscleController {

    @Autowired
    private BackMuscleService backMuscleService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllBackExercises() {
        List<Exercise> exercises = backMuscleService.getAllBackExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/lat")
    public ResponseEntity<List<Exercise>> getLatExercises() {
        List<Exercise> exercises = backMuscleService.getLatExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/mid")
    public ResponseEntity<List<Exercise>> getMidBackExercises() {
        List<Exercise> exercises = backMuscleService.getMidBackExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/trap")
    public ResponseEntity<List<Exercise>> getTrapExercises() {
        List<Exercise> exercises = backMuscleService.getTrapsExercises();
        return ResponseEntity.ok(exercises);
    }

    // Handle invalid input and resource not found
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

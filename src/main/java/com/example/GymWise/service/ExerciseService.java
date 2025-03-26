package com.example.GymWise.service;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise addExercise(Exercise exercise) {
        System.out.println("Incoming exercise: " + exercise);
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findByMuscleGroup(muscleGroup);
    }

    public List<Exercise> getBySubMuscleGroup(String subMuscleGroup) {
        return exerciseRepository.findBySubMuscleGroup(subMuscleGroup);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
}
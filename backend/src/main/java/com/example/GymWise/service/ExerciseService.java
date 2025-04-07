package com.example.GymWise.service;
import com.example.GymWise.entity.Exercise;
import com.example.GymWise.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise addExercise(Exercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Transactional
    public void removeExerciseByName(String name) {
        this.exerciseRepository.deleteByName(name);
    }

    public List<Exercise> getAllExercises() {
        return this.exerciseRepository.findAll();
    }

    public Exercise getByName(String name) {
        return this.exerciseRepository.findByName(name);
    }

    public List<Exercise> getByMuscleGroup(String muscleGroup) {
        return this.exerciseRepository.findByMuscleGroup(muscleGroup);
    }

    public List<Exercise> getBySubMuscleGroup(String subMuscleGroup) {
        return this.exerciseRepository.findBySubMuscleGroup(subMuscleGroup);
    }

}
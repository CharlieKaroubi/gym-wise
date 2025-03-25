package com.service;
import com.entity.Exercise;
import com.entity.muscle.MuscleGroup;
import com.repository.ExerciseRepository;
import com.repository.muscle.MuscleGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    // Find all exercises
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // Find exercises by muscle type
    public List<MuscleGroup> getExercisesByMuscleType(Class<? extends MuscleGroup> type) {
        return muscleGroupRepository.findByType(type);
    }

    // Add new exercise
    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // Delete exercise
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
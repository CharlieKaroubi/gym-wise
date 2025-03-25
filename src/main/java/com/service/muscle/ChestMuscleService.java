package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.ChestMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.ChestMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ChestMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ChestMuscleRepository chestMuscleRepository;

    public List<Exercise> getAllChestExercises() {
        return exerciseRepository.findByMuscleGroupIn(chestMuscleRepository.findAll());
    }

    public List<Exercise> getUpperChestExercises() {
        List<ChestMuscle> upperChestMuscles = chestMuscleRepository.findUpperChestMuscles();
        return exerciseRepository.findByMuscleGroupIn(upperChestMuscles);
    }

    public List<Exercise> getLowerChestExercises() {
        List<ChestMuscle> lowerChestMuscles = chestMuscleRepository.findLowerChestMuscles();
        return exerciseRepository.findByMuscleGroupIn(lowerChestMuscles);
    }
}
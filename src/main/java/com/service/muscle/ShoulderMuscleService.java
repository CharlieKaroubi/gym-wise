package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.ShoulderMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.ShoulderMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ShoulderMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ShoulderMuscleRepository shoulderMuscleRepository;

    public List<Exercise> getAllShoulderExercises() {
        return exerciseRepository.findByMuscleGroupIn(shoulderMuscleRepository.findAll());
    }

    public List<Exercise> getFrontDeltExercises() {
        List<ShoulderMuscle> frontDeltMuscles = shoulderMuscleRepository.findFrontDeltMuscles();
        return exerciseRepository.findByMuscleGroupIn(frontDeltMuscles);
    }

    public List<Exercise> getSideDeltExercises() {
        List<ShoulderMuscle> sideDeltMuscles = shoulderMuscleRepository.findSideDeltMuscles();
        return exerciseRepository.findByMuscleGroupIn(sideDeltMuscles);
    }

    public List<Exercise> getRearDeltExercises() {
        List<ShoulderMuscle> rearDeltMuscles = shoulderMuscleRepository.findRearDeltMuscles();
        return exerciseRepository.findByMuscleGroupIn(rearDeltMuscles);
    }
}
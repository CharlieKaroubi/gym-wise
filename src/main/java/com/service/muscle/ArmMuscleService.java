package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.ArmMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.ArmMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ArmMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ArmMuscleRepository armMuscleRepository;

    public List<Exercise> getAllArmExercises() {
        return exerciseRepository.findByMuscleGroupIn(armMuscleRepository.findAll());
    }

    public List<Exercise> getBicepExercises() {
        List<ArmMuscle> bicepMuscles = armMuscleRepository.findBicepMuscles();
        return exerciseRepository.findByMuscleGroupIn(bicepMuscles);
    }

    public List<Exercise> getTricepExercises() {
        List<ArmMuscle> tricepMuscles = armMuscleRepository.findTricepMuscles();
        return exerciseRepository.findByMuscleGroupIn(tricepMuscles);
    }

    public List<Exercise> getForearmExercises() {
        List<ArmMuscle> forearmsMuscles = armMuscleRepository.findForearmsMuscles();
        return exerciseRepository.findByMuscleGroupIn(forearmsMuscles);
    }
}
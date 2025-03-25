package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.CoreMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.CoreMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoreMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private CoreMuscleRepository coreMuscleRepository;

    public List<Exercise> getAllCoreExercises() {
        return exerciseRepository.findByMuscleGroupIn(coreMuscleRepository.findAll());
    }

    public List<Exercise> getUpperAbsExercises() {
        List<CoreMuscle> upperAbsMuscles = coreMuscleRepository.findUpperAbsMuscles();
        return exerciseRepository.findByMuscleGroupIn(upperAbsMuscles);
    }

    public List<Exercise> getLowerAbsExercises() {
        List<CoreMuscle> lowerAbsMuscles = coreMuscleRepository.findLowerAbsMuscles();
        return exerciseRepository.findByMuscleGroupIn(lowerAbsMuscles);
    }

    public List<Exercise> getObliquesExercises() {
        List<CoreMuscle> obliquesMuscles = coreMuscleRepository.findObliquesMuscles();
        return exerciseRepository.findByMuscleGroupIn(obliquesMuscles);
    }
}
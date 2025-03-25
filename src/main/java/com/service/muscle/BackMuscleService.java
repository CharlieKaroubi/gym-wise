package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.BackMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.BackMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BackMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private BackMuscleRepository backMuscleRepository;

    public List<Exercise> getAllBackExercises() {
        return exerciseRepository.findByMuscleGroupIn(backMuscleRepository.findAll());
    }

    public List<Exercise> getLatExercises() {
        List<BackMuscle> latMuscles = backMuscleRepository.findLatMuscles();
        return exerciseRepository.findByMuscleGroupIn(latMuscles);
    }

    public List<Exercise> getMidBackExercises() {
        List<BackMuscle> midBackMuscles = backMuscleRepository.findMidBackMuscles();
        return exerciseRepository.findByMuscleGroupIn(midBackMuscles);
    }

    public List<Exercise> getTrapsExercises() {
        List<BackMuscle> trapsMuscles = backMuscleRepository.findTrapsMuscles();
        return exerciseRepository.findByMuscleGroupIn(trapsMuscles);
    }
}
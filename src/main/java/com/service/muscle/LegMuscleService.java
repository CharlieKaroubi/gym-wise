package com.service.muscle;
import com.entity.Exercise;
import com.entity.muscle.LegMuscle;
import com.repository.ExerciseRepository;
import com.repository.muscle.LegMuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LegMuscleService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LegMuscleRepository legMuscleRepository;

    public List<Exercise> getAllLegExercises() {
        return exerciseRepository.findByMuscleGroupIn(legMuscleRepository.findAll());
    }

    public List<Exercise> getQuadExercises() {
        List<LegMuscle> quadMuscles = legMuscleRepository.findQuadMuscles();
        return exerciseRepository.findByMuscleGroupIn(quadMuscles);
    }

    public List<Exercise> getHamstringExercises() {
        List<LegMuscle> hamstringMuscles = legMuscleRepository.findHamstringMuscles();
        return exerciseRepository.findByMuscleGroupIn(hamstringMuscles);
    }

    public List<Exercise> getAdductorExercises() {
        List<LegMuscle> adductorMuscles = legMuscleRepository.findAdductorMuscles();
        return exerciseRepository.findByMuscleGroupIn(adductorMuscles);
    }

    public List<Exercise> getGluteExercises() {
        List<LegMuscle> gluteMuscles = legMuscleRepository.findGluteMuscles();
        return exerciseRepository.findByMuscleGroupIn(gluteMuscles);
    }

    public List<Exercise> getCalfExercises() {
        List<LegMuscle> calfMuscles = legMuscleRepository.findCalfMuscles();
        return exerciseRepository.findByMuscleGroupIn(calfMuscles);
    }
}
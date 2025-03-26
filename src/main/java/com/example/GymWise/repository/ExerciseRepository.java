package com.example.GymWise.repository;
import com.example.GymWise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(String muscleGroup);
    List<Exercise> findBySubMuscleGroup(String subMuscleGroup);

}

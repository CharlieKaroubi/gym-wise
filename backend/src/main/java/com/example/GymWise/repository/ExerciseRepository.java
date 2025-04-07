package com.example.GymWise.repository;
import com.example.GymWise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(String muscleGroup);
    List<Exercise> findBySubMuscleGroup(String subMuscleGroup);
    void deleteByName(String name);
    Exercise findByName(String name);
}

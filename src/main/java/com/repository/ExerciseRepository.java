package com.repository;
import com.entity.Exercise;
import com.entity.muscle.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
}

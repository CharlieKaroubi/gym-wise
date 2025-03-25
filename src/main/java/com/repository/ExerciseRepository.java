package com.repository;
import com.entity.Exercise;
import com.entity.muscle.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
    List<Exercise> findByMuscleGroupIn(List<? extends MuscleGroup> muscleGroups);

    List<Exercise> findByDifficultyGreaterThan(BigDecimal difficulty);
    List<Exercise> findByDifficultyLessThan(BigDecimal difficulty);

    List<Exercise> findByEffectivenessGreaterThan(BigDecimal effectiveness);
    List<Exercise> findByEffectivenessLessThan(BigDecimal effectiveness);

    List<Exercise> findByDifficultyBetween(BigDecimal minDifficulty, BigDecimal maxDifficulty);

    List<Exercise> findByEffectivenessBetween(BigDecimal minEffectiveness, BigDecimal maxEffectiveness);

    List<Exercise> findByDifficultyBetweenAndEffectivenessBetween(
            BigDecimal minDifficulty,
            BigDecimal maxDifficulty,
            BigDecimal minEffectiveness,
            BigDecimal maxEffectiveness
    );
}

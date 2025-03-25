package com.repository.muscle;

import com.entity.muscle.BackMuscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BackMuscleRepository extends JpaRepository<BackMuscle, Long> {
    //  Get all back muscles
    List<BackMuscle> findAll();

    //  Find lat muscles
    @Query("SELECT b FROM BackMuscle b WHERE b.targetsLats = true")
    List<BackMuscle> findLatMuscles();

    //  Find mid back muscles
    @Query("SELECT b FROM BackMuscle b WHERE b.targetsMidBack = true")
    List<BackMuscle> findMidBackMuscles();

    //  Find traps muscles
    @Query("SELECT b FROM BackMuscle b WHERE b.targetsTraps = true")
    List<BackMuscle> findTrapsMuscles();

}
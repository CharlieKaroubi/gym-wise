package com.repository.muscle;

import com.entity.muscle.CoreMuscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoreMuscleRepository extends JpaRepository<CoreMuscle, Long> {
    //  Get all core muscles
    List<CoreMuscle> findAll();

    //  Find upper abs muscles
    @Query("SELECT c FROM CoreMuscle c WHERE c.targetsUpperAbs = true")
    List<CoreMuscle> findUpperAbsMuscles();

    //  Find lower abs muscles
    @Query("SELECT c FROM CoreMuscle c WHERE c.targetsLowerAbs = true")
    List<CoreMuscle> findLowerAbsMuscles();

    //  Find obliques muscles
    @Query("SELECT c FROM CoreMuscle c WHERE c.targetsObliques = true")
    List<CoreMuscle> findObliquesMuscles();
}

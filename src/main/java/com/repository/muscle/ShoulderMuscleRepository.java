package com.repository.muscle;

import com.entity.muscle.ShoulderMuscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoulderMuscleRepository extends JpaRepository<ShoulderMuscle, Long> {
    //  Get all shoulder muscles
    List<ShoulderMuscle> findAll();

    //  Find front delt muscles
    @Query("SELECT s FROM ShoulderMuscle s WHERE s.targetsFrontDeltoid = true")
    List<ShoulderMuscle> findFrontDeltMuscles();

    //  Find side delt muscles
    @Query("SELECT s FROM ShoulderMuscle s WHERE s.targetsSideDeltoid = true")
    List<ShoulderMuscle> findSideDeltMuscles();

    //  Find rear delt muscles
    @Query("SELECT s FROM ShoulderMuscle s WHERE s.targetsRearDeltoid = true")
    List<ShoulderMuscle> findRearDeltMuscles();

}
package com.repository.muscle;

import com.entity.muscle.ChestMuscle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChestMuscleRepository extends JpaRepository<ChestMuscle, Long> {
    //  Get all chest muscles
    List<ChestMuscle> findAll();

    //  Find upper abs muscles
    @Query("SELECT c FROM ChestMuscle c WHERE c.targetsUpperChest = true")
    List<ChestMuscle> findUpperChestMuscles();

    //  Find lower chest muscles
    @Query("SELECT c FROM ChestMuscle c WHERE c.targetsLowerChest = true")
    List<ChestMuscle> findLowerChestMuscles();
}

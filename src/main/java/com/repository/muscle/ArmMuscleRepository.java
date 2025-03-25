package com.repository.muscle;

import com.entity.muscle.ArmMuscle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArmMuscleRepository extends JpaRepository<ArmMuscle, Long> {
    //  Get all arm muscles
    List<ArmMuscle> findAll();

    //  Find bicep muscles
    @Query("SELECT a FROM ArmMuscle a WHERE a.targetsBiceps = true")
    List<ArmMuscle> findBicepMuscles();

    //  Find tricep muscles
    @Query("SELECT a FROM ArmMuscle a WHERE a.targetsTriceps = true")
    List<ArmMuscle> findTricepMuscles();

    //  Find forearm muscles
    @Query("SELECT a FROM ArmMuscle a WHERE a.targetsForearms = true")
    List<ArmMuscle> findForearmsMuscles();

}
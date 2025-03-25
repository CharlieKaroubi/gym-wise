package com.repository.muscle;

import com.entity.muscle.LegMuscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LegMuscleRepository extends JpaRepository<LegMuscle, Long> {
    //  Get all leg muscles
    List<LegMuscle> findAll();

    //Find quad muscles
    @Query("SELECT l FROM LegMuscle l WHERE l.targetsQuads = true")
    List<LegMuscle> findQuadMuscles();

    //Find hamstring muscles
    @Query("SELECT l FROM LegMuscle l WHERE l.targetsHamstrings = true")
    List<LegMuscle> findHamstringMuscles();

    //Find calf muscles
    @Query("SELECT l FROM LegMuscle l WHERE l.targetsCalves = true")
    List<LegMuscle> findCalfMuscles();

    //Find glute muscles
    @Query("SELECT l FROM LegMuscle l WHERE l.targetsGlutes = true")
    List<LegMuscle> findGluteMuscles();

    //Find adductor muscles
    @Query("SELECT l FROM LegMuscle l WHERE l.targetsAdductors = true")
    List<LegMuscle> findAdductorMuscles();
}

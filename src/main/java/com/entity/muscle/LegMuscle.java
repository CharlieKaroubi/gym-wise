package com.entity.muscle;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LegMuscle extends MuscleGroup {

    private boolean targetsQuads;
    private boolean targetsHamstrings;
    private boolean targetsCalves;
    private boolean targetsGlutes;
    private boolean targetsAdductors;
}

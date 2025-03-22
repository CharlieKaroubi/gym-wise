package com.entity.muscle;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShoulderMuscle extends MuscleGroup {
    private boolean targetsFrontDeltoid;
    private boolean targetsSideDeltoid;
    private boolean targetsRearDeltoid;
}

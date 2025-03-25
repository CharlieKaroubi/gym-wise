package com.entity.muscle;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChestMuscle extends MuscleGroup {
    private boolean targetsUpperChest;
    private boolean targetsLowerChest;
}

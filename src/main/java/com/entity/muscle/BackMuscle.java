package com.entity.muscle;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BackMuscle extends MuscleGroup {
    private boolean targetsLats;
    private boolean targetsTraps;
    private boolean targetsMidBack;
}

package com.entity.muscle;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CoreMuscle extends MuscleGroup {

    private boolean targetsUpperAbs;
    private boolean targetsLowerAbs;
    private boolean targetsObliques;
}


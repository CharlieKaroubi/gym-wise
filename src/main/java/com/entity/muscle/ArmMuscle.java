package com.entity.muscle;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArmMuscle extends MuscleGroup {
    private boolean targetsBiceps;
    private boolean targetsTriceps;
    private boolean targetsForearms;
}

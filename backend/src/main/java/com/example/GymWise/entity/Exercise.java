package com.example.GymWise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String force;

    private String level;

    private String mechanic;

    private String equipment;

    private String instructions;

    private String primaryMuscles;

    private String secondaryMuscles;

    public Exercise(String name,
                    String force,
                    String level,
                    String secondaryMuscles,
                    String primaryMuscles,
                    String instructions,
                    String mechanic,
                    String equipment) {
        this.name = name;
        this.force = force;
        this.level = level;
        this.secondaryMuscles = secondaryMuscles;
        this.primaryMuscles = primaryMuscles;
        this.instructions = instructions;
        this.mechanic = mechanic;
        this.equipment = equipment;
    }

    public Exercise() {}
}

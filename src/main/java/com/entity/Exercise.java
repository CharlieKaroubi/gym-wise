package com.entity;

import com.entity.muscle.MuscleGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroup muscleGroup;

    @Column(precision = 3, scale = 2)
    private BigDecimal difficulty;

    @Column(precision = 3, scale = 2)
    private BigDecimal effectiveness;

    @Lob
    private String videoUrl;

    @Lob
    private String articleUrl;

}

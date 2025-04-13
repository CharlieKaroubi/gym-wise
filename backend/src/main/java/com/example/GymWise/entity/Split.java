package com.example.GymWise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "splits")
@Entity
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String concentration;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> days = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @JdbcTypeCode(SqlTypes.OTHER)
    @Column(columnDefinition = "vector(1536)")
    private List<Double> embedding;

    public Split(String name, String description, String concentration, List<Day> days, User creator, List<Double> embedding) {
        this.name = name;
        this.description = description;
        this.concentration = concentration;
        this.days = days;
        this.creator = creator;
        this.embedding = embedding;
    }

    public Split() {}
}


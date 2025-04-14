package com.example.GymWise.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.pgvector.PGvector;

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

    @OneToMany(mappedBy = "split", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Day> days = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public Split(String name, String description, String concentration, List<Day> days, User creator) {
        this.name = name;
        this.description = description;
        this.concentration = concentration;
        this.days = days;
        this.creator = creator;
    }

    public Split() {}
}


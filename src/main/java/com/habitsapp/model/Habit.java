package com.habitsapp.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "HABIT")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FREQUENCY")
    private Frequency frequency;

    @Column(name = "TRACK")
    private boolean track;

    @Column(name = "PROGRESS")
    private Progress progress;

}

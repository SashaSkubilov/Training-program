package com.example.training_program.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Общие поля
    private String name;
    private String type; // Здесь будем писать: "REST", "WEIGHT", "VO2MAX" и т.д.

    // Для силовых (TechExercise, weightExercise)
    private Integer sets;
    private Integer reps;
    private Double weight;

    // Для бега и выносливости (VO2MAXExercise, tempExercise)
    private Integer distance;
    private Integer pace;
    private Integer recovery; // время восстановления

    // Для изометрии и отдыха (RestPeriod, IsometricExercise)
    private Integer minutes;
    private Integer seconds;

    private String muscleGroup;
}
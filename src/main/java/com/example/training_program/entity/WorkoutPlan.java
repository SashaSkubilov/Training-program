package com.example.training_program.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Например: "Набор массы" или "Похудение"

    // Связь Many-to-One: Много планов могут принадлежать одному пользователю
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
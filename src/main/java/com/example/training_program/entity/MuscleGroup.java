package com.example.training_program.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MuscleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // У одной группы мышц (например, Спина) может быть много упражнений
    @OneToMany(mappedBy = "muscleGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY) // Указываем явно для 4-го пункта
    private List<Exercise> exercises;
}
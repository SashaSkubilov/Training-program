package com.example.training_program.dto;

import lombok.Data;

@Data // Эта магия создаст setId, getId и прочие методы автоматически
public class ExerciseDto {
    private Long id;
    private String name;
    private String muscleGroupName;
}
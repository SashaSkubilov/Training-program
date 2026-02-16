package com.example.training_program.dto;

import lombok.Data;

@Data // Генерирует геттеры и сеттеры
public class ExerciseDto {
    private String name;
    private String description; // Сюда мы "склеим" подходы, веса и время
}
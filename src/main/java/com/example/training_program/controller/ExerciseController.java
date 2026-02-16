package com.example.training_program.controller;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Говорим Spring, что этот класс отвечает за API
@RequestMapping("/api/exercises") // Базовый адрес для всех запросов в этом классе
public class ExerciseController {

    @Autowired
    private ExerciseService service;

    // 1. GET endpoint с @PathVariable
    // Пример вызова: http://localhost:8080/api/exercises/1
    // Мы берем ID прямо из части ссылки
    @GetMapping("/{id}")
    public ExerciseDto getExerciseById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 2. GET endpoint с @RequestParam
    // Пример вызова: http://localhost:8080/api/exercises?muscleGroup=Back
    // Мы берем параметры после знака вопроса
    @GetMapping
    public List<ExerciseDto> getExercisesByGroup(@RequestParam String muscleGroup) {
        return service.getByMuscleGroup(muscleGroup);
    }
}
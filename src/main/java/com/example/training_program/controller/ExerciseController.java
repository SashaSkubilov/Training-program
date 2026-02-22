package com.example.training_program.controller;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService service;

    @GetMapping("/{id}")
    public ExerciseDto getExerciseById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ExerciseDto> getExercisesByGroup(@RequestParam String muscleGroup) {
        return service.getByMuscleGroup(muscleGroup);
    }

    @PostMapping
    public ExerciseDto createExercise(@RequestBody ExerciseDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ExerciseDto updateExercise(@PathVariable Long id, @RequestBody ExerciseDto dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<ExerciseDto> search(
            @RequestParam String group,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.searchWithIndex(group, page, size);
    }
}
package com.example.training_program.controller;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.service.ExerciseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

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
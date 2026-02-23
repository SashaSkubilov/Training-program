package com.example.training_program.mapper;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.entity.Exercise;
import com.example.training_program.entity.MuscleGroup;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public ExerciseDto toDto(Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        if (exercise.getMuscleGroup() != null) {
            dto.setMuscleGroupName(exercise.getMuscleGroup().getName());
        }
        return dto;
    }

    public Exercise toEntity(ExerciseDto dto, MuscleGroup muscleGroup) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setMuscleGroup(muscleGroup);
        return exercise;
    }
}

package com.example.training_program.service;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.model.Exercise;
import com.example.training_program.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    // Тот самый "Маппер" (Пункт 5 задания)
    // Превращаем сложную Entity в понятный пользователю DTO
    public ExerciseDto convertToDto(Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setName(exercise.getName());

        // Логика формирования описания, как в твоих display() на C++
        String info = switch (exercise.getType().toUpperCase()) {
            case "WEIGHT" -> exercise.getSets() + "x" + exercise.getReps() + " - " + exercise.getWeight() + "кг";
            case "REST" -> "Отдых: " + exercise.getMinutes() + " мин";
            case "VO2MAX" -> "Бег: " + exercise.getDistance() + "м, темп: " + exercise.getPace() + " сек/км";
            default -> exercise.getSets() + " подходов";
        };

        dto.setDescription(info);
        return dto;
    }

    // Метод для поиска по ID (для PathVariable)
    public ExerciseDto getById(Long id) {
        return repository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    // Метод для поиска по группе мышц (для RequestParam)
    public List<ExerciseDto> getByMuscleGroup(String group) {
        return repository.findByMuscleGroup(group).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
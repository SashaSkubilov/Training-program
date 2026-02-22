package com.example.training_program.service;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.dto.SearchKey;
import com.example.training_program.entity.Exercise;
import com.example.training_program.repository.ExerciseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;
    private final Map<SearchKey, List<ExerciseDto>> index = new HashMap<>();

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    // --- Методы из 2-й лабы (которые сейчас выдают ошибки) ---

    public ExerciseDto getById(Long id) {
        return repository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public List<ExerciseDto> getByMuscleGroup(String groupName) {
        return repository.findByMuscleGroupName(groupName)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ExerciseDto save(ExerciseDto dto) {
        clearIndex(); // Инвалидация кэша для 3-й лабы
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        // Здесь должна быть логика поиска группы мышц, как мы делали раньше
        Exercise saved = repository.save(exercise);
        return convertToDto(saved);
    }

    public void delete(Long id) {
        clearIndex(); // Инвалидация кэша для 3-й лабы
        repository.deleteById(id);
    }

    // --- Новые методы для 3-й лабы ---

    public List<ExerciseDto> searchWithIndex(String groupName, int page, int size) {
        SearchKey key = new SearchKey(groupName, page, size);

        if (index.containsKey(key)) {
            System.out.println(">>> Взято из КЭША");
            return index.get(key);
        }

        System.out.println(">>> Запрос к БАЗЕ");
        Pageable pageable = PageRequest.of(page, size);

        // Используем твой метод findAll(pageable) из репозитория
        List<ExerciseDto> results = repository.findAll(pageable)
                .stream()
                .filter(e -> e.getMuscleGroup().getName().equalsIgnoreCase(groupName))
                .map(this::convertToDto)
                .collect(Collectors.toList());

        System.out.println("Найдены упражнения: " + results.size() + " шт.");

        index.put(key, results);
        return results;
    }

    public void clearIndex() {
        index.clear();
        System.out.println(">>> Индекс очищен");
    }

    private ExerciseDto convertToDto(Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        if (exercise.getMuscleGroup() != null) {
            dto.setMuscleGroupName(exercise.getMuscleGroup().getName());
        }
        return dto;
    }
}
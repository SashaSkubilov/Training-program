package com.example.training_program.service;

import com.example.training_program.dto.ExerciseDto;
import com.example.training_program.dto.SearchKey;
import com.example.training_program.entity.Exercise;
import com.example.training_program.entity.MuscleGroup;
import com.example.training_program.mapper.ExerciseMapper;
import com.example.training_program.repository.ExerciseRepository;
import com.example.training_program.repository.MuscleGroupRepository;
import java.lang.RuntimeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseService.class);

    private final ExerciseRepository repository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseMapper mapper;
    private final Map<SearchKey, List<ExerciseDto>> index = new HashMap<>();

    public ExerciseService(
            ExerciseRepository repository,
            MuscleGroupRepository muscleGroupRepository,
            ExerciseMapper mapper) {
        this.repository = repository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.mapper = mapper;
    }

    // --- Методы из 2-й лабы (которые сейчас выдают ошибки) ---

    public ExerciseDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));
    }

    public List<ExerciseDto> getByMuscleGroup(String groupName) {
        return repository.findByMuscleGroupName(groupName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ExerciseDto save(ExerciseDto dto) {

        clearIndex();
        MuscleGroup muscleGroup = muscleGroupRepository.findByNameIgnoreCase(dto.getMuscleGroupName())
                .orElseThrow(() -> new IllegalArgumentException("Muscle group not found"));

        Exercise exercise = mapper.toEntity(dto, muscleGroup);
        Exercise saved = repository.save(exercise);

        return mapper.toDto(saved);
    }

    public void delete(Long id) {
        clearIndex(); // Инвалидация кэша для 3-й лабы
        repository.deleteById(id);
    }

    // --- Новые методы для 3-й лабы ---

    public List<ExerciseDto> searchWithIndex(String groupName, int page, int size) {
        SearchKey key = new SearchKey(groupName, page, size);

        if (index.containsKey(key)) {
            LOGGER.info("Data returned from cache for group={} page={} size={}", groupName, page, size);
            return index.get(key);
        }


        Pageable pageable = PageRequest.of(page, size);

        // Используем твой метод findAll(pageable) из репозитория
        List<ExerciseDto> results = repository.findAll(pageable)
                .stream()
                .filter(e -> e.getMuscleGroup().getName().equalsIgnoreCase(groupName))
                .map(mapper::toDto)
                .collect(Collectors.toList());

        LOGGER.info("Data returned from DB for group={} found={}", groupName, results.size());

        index.put(key, results);
        return results;
    }

    public void clearIndex() {
        index.clear();
        LOGGER.info("Search index was cleared");
    }
}
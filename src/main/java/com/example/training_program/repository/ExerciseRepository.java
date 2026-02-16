package com.example.training_program.repository;

import com.example.training_program.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // Метод для поиска упражнений по группе мышц
    // Это нужно для выполнения пункта 3 твоего задания (@RequestParam)
    List<Exercise> findByMuscleGroup(String muscleGroup);
}
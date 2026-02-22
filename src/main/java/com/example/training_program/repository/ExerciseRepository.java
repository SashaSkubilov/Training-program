package com.example.training_program.repository;

import com.example.training_program.entity.Exercise;
import org.springframework.data.domain.Page; // Не забудь этот импорт
import org.springframework.data.domain.Pageable; // И этот
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @EntityGraph(attributePaths = {"muscleGroup"})
    List<Exercise> findByMuscleGroupName(String muscleGroup);

    @EntityGraph(attributePaths = {"muscleGroup", "equipments"})
    List<Exercise> findAll();

    // 1. JPQL (Java Persistence Query Language)
    @Query("SELECT e FROM Exercise e WHERE e.muscleGroup.name = :name")
    List<Exercise> searchByGroupNameJPQL(@Param("name") String name);

    // 2. Native Query (Чистый SQL)
    @Query(value = "SELECT e.* FROM exercise e " +
            "JOIN muscle_group mg ON e.muscle_group_id = mg.id " +
            "WHERE mg.name = :name", nativeQuery = true)
    List<Exercise> searchByGroupNameNative(@Param("name") String name);

    // 3. ПАГИНАЦИЯ (Добавляем сюда)
    // Этот метод позволит Spring автоматически нарезать данные на страницы
    Page<Exercise> findAll(Pageable pageable);
}
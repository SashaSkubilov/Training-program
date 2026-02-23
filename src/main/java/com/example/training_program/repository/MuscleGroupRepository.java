package com.example.training_program.repository;

import com.example.training_program.entity.MuscleGroup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {

    Optional<MuscleGroup> findByNameIgnoreCase(String name);
}
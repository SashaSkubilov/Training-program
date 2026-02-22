package com.example.training_program.service;

import com.example.training_program.entity.User;
import com.example.training_program.entity.WorkoutPlan;
import com.example.training_program.repository.UserRepository;
import com.example.training_program.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WorkoutPlanRepository planRepository;

    public UserService(UserRepository userRepository, WorkoutPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Transactional
    public void createUserAndPlan(String username, String planTitle) {
        // 1. Сохраняем пользователя
        User user = new User();
        user.setUsername(username);
        user.setEmail(username + "@test.com");
        userRepository.save(user);

        // 2. Искусственная ошибка для демонстрации отката (Rollback)
        // Если название плана — "error", выкидываем ошибку
        if ("error".equalsIgnoreCase(planTitle)) {
            throw new RuntimeException("Специальная ошибка для проверки @Transactional");
        }

        // 3. Сохраняем план
        WorkoutPlan plan = new WorkoutPlan();
        plan.setTitle(planTitle);
        plan.setUser(user);
        planRepository.save(plan);
    }
}
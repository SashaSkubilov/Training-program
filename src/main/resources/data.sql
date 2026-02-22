-- 1. Добавляем группы мышц
INSERT INTO muscle_group (name)
VALUES ('Спина');
INSERT INTO muscle_group (name)
VALUES ('Грудь');

-- 2. Добавляем упражнения (привязываем к id групп мышц)
INSERT INTO exercise (name, muscle_group_id)
VALUES ('Подтягивания', 1);
INSERT INTO exercise (name, muscle_group_id)
VALUES ('Жим лежа', 2);

-- 3. Добавляем оборудование
INSERT INTO equipment (name)
VALUES ('Турник');
INSERT INTO equipment (name)
VALUES ('Штанга');

-- 4. Связываем упражнения с оборудованием (ManyToMany)
INSERT INTO exercise_equipment (exercise_id, equipment_id)
VALUES (1, 1);
INSERT INTO exercise_equipment (exercise_id, equipment_id)
VALUES (2, 2);

-- Добавляем пользователя
INSERT INTO users (username, email)
VALUES ('Sasha', 'sasha@example.com');

-- Добавляем план тренировок для пользователя с id=1
INSERT INTO workout_plan (title, user_id)
VALUES ('Силовая тренировка', 1);
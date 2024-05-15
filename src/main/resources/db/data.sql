DELETE FROM vote;
DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM restaurant;

INSERT INTO users (name, email, login, password)
VALUES  ('Some Admin', 'admin@gmail.com', 'admin', 'admin'),
        ('Some User', 'user@gmail.com', 'user1', 'password'),
        ('Some Another User', 'another_user@gmail.com', 'user2', 'password123');

INSERT INTO user_role (user_id, role)
VALUES  (0, 'ADMIN'),
        (0, 'USER'),
        (1, 'USER'),
        (2, 'USER');

INSERT INTO restaurant (name, address)
VALUES ('Cool Resto', '26 Cool Street'),
       ('Cooler Resto', '28 Cool Street');

INSERT INTO menu (day, restaurant_id)
VALUES (current_date, 0),
       (current_date, 1),
       ('2024-03-05', 0);

INSERT INTO dish (name, price, menu_id)
VALUES ('First Dish of First Menu', 100, 0),
       ('Second Dish of First Menu', 150, 0),
       ('First Dish of Second Menu', 200, 1),
       ('Second Dish of Second Menu', 250, 1);

INSERT INTO vote (day, voted_time, user_id, restaurant_id)
VALUES  ('2024-03-05', current_time, 1, 0),
        (current_date, current_time, 1, 1);


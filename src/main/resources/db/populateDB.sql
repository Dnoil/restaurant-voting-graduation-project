DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM restaurant;

INSERT INTO users (name, email, login, password)
VALUES ('User', 'user@yandex.ru', 'userlogin', 'password'),
       ('Admin', 'admin@gmail.com', 'adminlogin', 'admin');

INSERT INTO user_role (user_id, role)
VALUES (0, 'ADMIN'),
       (1, 'USER');

INSERT INTO restaurant (name, address)
VALUES ('Cool Restaurant', '28 Cool street'),
       ('Cooler Restaurant', '29 Cool street');

INSERT INTO menu (name, date, restaurant_id)
VALUES ('Cool Menu', '2024-03-05 09:22:06', 0),
       ('Cooler Menu', '2024-03-05 09:20:00', 1);

INSERT INTO dish (name, price, menu_id)
VALUES ('Cool Dish', 199, 0),
       ('Cooler Dish', 200, 1);

INSERT INTO vote (name, voted_date_time, user_id, restaurant_id)
VALUES ('first', now(), 0, 0);

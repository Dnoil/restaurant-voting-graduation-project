DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM user;
DELETE FROM menu;
DELETE FROM restaurant;

INSERT INTO user (name, email, login, password)
VALUES ('User', 'user@yandex.ru', 'userlogin', 'password'),
       ('Admin', 'admin@gmail.com', 'adminlogin', 'admin');

INSERT INTO user_role (user_id, role)
VALUES (0, 'USER'),
       (1, 'ADMIN');

INSERT INTO restaurant (name, address)
VALUES ('Cool Restaurant', '28 Cool street'),
       ('Cooler Restaurant', '29 Cool street');

INSERT INTO menu (name, date, menu_restaurant)
VALUES ('Cool Menu', '2024-03-05 09:22:06', 0),
       ('Cooler Menu', '2024-03-05 09:20:00', 1);

INSERT INTO dish (name, price, dish_menu)
VALUES ('Cool Restaurant', 199, 0),
       ('Cooler Restaurant', 200, 1);

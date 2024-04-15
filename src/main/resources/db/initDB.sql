DROP TABLE user_role IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    login      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE user_role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT unique_user_roles UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    CONSTRAINT unique_address UNIQUE (address)
);
CREATE UNIQUE INDEX restaurant_name_idx ON restaurant (name);

CREATE TABLE menu
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    date          TIMESTAMP    NOT NULL,
    restaurant_id INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    CONSTRAINT single_menu_per_restaurant UNIQUE (restaurant_id)
);
CREATE UNIQUE INDEX menu_name_idx ON menu (name);

CREATE TABLE dish
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   VARCHAR(255) NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
    CONSTRAINT unique_dish_in_menu UNIQUE (name, menu_id)
);

CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255)            NOT NULL,
    voted_time    TIMESTAMP DEFAULT now() NOT NULL,
    user_id       INTEGER                 NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    CONSTRAINT single_vote_per_user UNIQUE (user_id)
);

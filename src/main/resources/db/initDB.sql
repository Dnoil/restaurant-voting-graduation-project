DROP TABLE user_role IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE user IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restaurant IF EXISTS;

CREATE TABLE user
(
    id               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    login            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOLEAN   DEFAULT TRUE  NOT NULL,
    voted            BOOLEAN   DEFAULT FALSE NOT NULL,
    voted_time       TIMESTAMP
);
CREATE UNIQUE INDEX user_unique_email_idx ON user (email);
CREATE UNIQUE INDEX user_unique_login_idx ON user (login);
CREATE UNIQUE INDEX user_unique_voted_idx ON user (voted);

CREATE TABLE user_role
(
    user_id     INTEGER         NOT NULL,
    role        VARCHAR(255)    NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id                  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                VARCHAR(255)    NOT NULL,
    address             VARCHAR(255)    NOT NULL,
    voted_popularity    VARCHAR(255)
);
CREATE UNIQUE INDEX restaurant_name_voted_idx ON restaurant (name);

CREATE TABLE menu
(
    id                  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                VARCHAR(255)    NOT NULL,
    date                TIMESTAMP       NOT NULL,
    menu_restaurant     INTEGER         NOT NULL,
    FOREIGN KEY (menu_restaurant) REFERENCES restaurant (id)
);
CREATE UNIQUE INDEX menu_name_voted_idx ON menu (name);

CREATE TABLE dish
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL,
    price       VARCHAR(255)    NOT NULL,
    dish_menu   INTEGER         NOT NULL,
    FOREIGN KEY (dish_menu) REFERENCES menu (id)
);

CREATE TABLE todo
(
    id           bigint      NOT NULL PRIMARY KEY auto_increment,
    text         VARCHAR(255),
    done         boolean NOT NULL
);
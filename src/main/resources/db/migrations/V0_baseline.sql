--liquibase formatted sql

--changeset MarkNomann:V0

CREATE TABLE IF NOT EXISTS city (
    id BIGINT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    city_id BIGINT REFERENCES city(id)
);

--liquibase formatted sql

--changeset MarkNomann:V1

--CREATE SEQUENCE IF NOT EXISTS street_seq START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS street (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city_id BIGINT REFERENCES city(id)
)



--liquibase formatted sql

--changeset MarkNomann:V2

CREATE TABLE IF NOT EXISTS neighbors (
    street_id BIGINT,
    neighbor_id BIGINT,
    PRIMARY KEY (street_id,neighbor_id),
    CONSTRAINT fk_street_id FOREIGN KEY (street_id) REFERENCES  street(id),
    CONSTRAINT fk_neighbor_id FOREIGN KEY (neighbor_id) REFERENCES street(id)
);
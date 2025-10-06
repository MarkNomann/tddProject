CREATE TABLE city (
    id INT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE person (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    city_id INT REFERENCES city(id)
);

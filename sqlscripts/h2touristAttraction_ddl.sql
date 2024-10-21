-- Drop tables if they already exist
DROP TABLE IF EXISTS touristattraction_tag;
DROP TABLE IF EXISTS touristattraction;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS tag;

-- Create city table
CREATE TABLE city
(
    cityId INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(255)
);

-- Create tag table
CREATE TABLE tag
(
    tag_id   INT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(255) NOT NULL
);

-- Create touristattraction table
CREATE TABLE touristattraction
(
    tourist_id  INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    prisDollar  INT,
    cityId      INT,
    FOREIGN KEY (cityId) REFERENCES city (cityId)
);

-- Create touristattraction_tag table
CREATE TABLE touristattraction_tag
(
    tag_id     INT,
    tourist_id INT,
    PRIMARY KEY (tag_id, tourist_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id),
    FOREIGN KEY (tourist_id) REFERENCES touristattraction (tourist_id)
);

-- Insert values into city table
INSERT INTO city(name)
VALUES ('Roskilde'),
       ('Copenhagen'),
       ('Norrebro');

-- Insert values into tag table
INSERT INTO tag (tag_name)
VALUES ('Forlystelse'),
       ('Ballon'),
       ('Natur');

-- Insert values into touristattraction table
INSERT INTO touristattraction(name, description, prisDollar, cityId)
VALUES ('Tivoli', 'an amusement park', 10, 1);

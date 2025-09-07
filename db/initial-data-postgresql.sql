-- Create database
CREATE DATABASE IF NOT EXISTS multiple_datasource_postgresql;

-- Connect to the database
\c multiple_datasource_postgresql;

-- Create tables
CREATE TABLE IF NOT EXISTS bug (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS flower (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS season (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Insert initial data
INSERT INTO bug (name) VALUES 
('Ladybug'),
('Butterfly'),
('Beetle'),
('Grasshopper'),
('Ant');

INSERT INTO flower (name) VALUES 
('Rose'),
('Tulip'),
('Daisy'),
('Sunflower'),
('Lily');

INSERT INTO season (name) VALUES 
('Spring'),
('Summer'),
('Fall'),
('Winter');

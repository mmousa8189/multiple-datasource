-- Create database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'multiple_datasource_sqlserver')
BEGIN
    CREATE DATABASE multiple_datasource_sqlserver;
END
GO

-- Use the database
USE multiple_datasource_sqlserver;
GO

-- Create tables
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[bug]') AND type in (N'U'))
BEGIN
    CREATE TABLE bug (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(255) NOT NULL
    );
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[flower]') AND type in (N'U'))
BEGIN
    CREATE TABLE flower (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(255) NOT NULL
    );
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[season]') AND type in (N'U'))
BEGIN
    CREATE TABLE season (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(255) NOT NULL
    );
END
GO

-- Insert initial data
-- Clear existing data first to avoid duplicates
DELETE FROM bug;
DELETE FROM flower;
DELETE FROM season;
GO

-- Insert data into bug table
INSERT INTO bug (name) VALUES 
('Ladybug'),
('Butterfly'),
('Beetle'),
('Grasshopper'),
('Ant');
GO

-- Insert data into flower table
INSERT INTO flower (name) VALUES 
('Rose'),
('Tulip'),
('Daisy'),
('Sunflower'),
('Lily');
GO

-- Insert data into season table
INSERT INTO season (name) VALUES 
('Spring'),
('Summer'),
('Fall'),
('Winter');
GO

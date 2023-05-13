-- Active: 1657020369972@@127.0.0.1@3306@my_data

CREATE DATABASE IF NOT EXISTS hr_data ;

USE hr_data ;

CREATE TABLE
    admin(
        id int PRIMARY KEY NOT NULL,
        name VARCHAR(255),
        password VARCHAR(255)
    );

INSERT INTO admin VALUES ("1","admin", "admin");

INSERT INTO admin VALUES ("2","admin", "");

CREATE TABLE
    employees (
        emp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50),
        department VARCHAR(50),
        address VARCHAR(100),
        salary DECIMAL(12, 2),
        gender VARCHAR(128),
        admin_id INT NOT NULL,
        FOREIGN KEY (admin_id) REFERENCES admin(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

-- For Testing

INSERT INTO
    employees (
        name,
        department,
        address,
        salary,
        gender,
        admin_id
    )
VALUES (
        'John Doe',
        'Sales',
        '123 Main St, Anytown USA',
        50000.00,
        "male",
        1
    );

CREATE Table
    employees_details(
        id int PRIMARY KEY NOT NULL,
        emp_img BLOB,
        email VARCHAR(255),
        phone VARCHAR(255),
        emp_id int NOT NULL,
        Foreign Key (emp_id) REFERENCES employees (emp_id) ON DELETE CASCADE ON UPDATE CASCADE
    );
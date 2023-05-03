-- Active: 1683132803907@@127.0.0.1@3306@my_data

CREATE DATABASE IF NOT EXISTS my_data ;

USE my_data ;

use my_data ;

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
        admin_id
    )
VALUES (
        'John Doe',
        'Sales',
        '123 Main St, Anytown USA',
        50000.00,
        1
    );
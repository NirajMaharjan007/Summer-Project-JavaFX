-- Active: 1683132803907@@127.0.0.1@3306@hr_data

CREATE DATABASE IF NOT EXISTS hr_data ;

USE hr_data ;

CREATE TABLE
    admin(
        id int PRIMARY KEY NOT NULL,
        name VARCHAR(255),
        password VARCHAR(255)
    );

INSERT INTO admin VALUES ("1","admin", "");

INSERT INTO admin VALUES ("2","admin", "admin");

CREATE TABLE
    employees (
        emp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50),
        department VARCHAR(50),
        address VARCHAR(100),
        salary DECIMAL(12, 2),
        gender VARCHAR(128),
        email VARCHAR(255),
        phone VARCHAR(255),
        emp_img BLOB,
        admin_id INT NOT NULL,
        FOREIGN KEY (admin_id) REFERENCES admin(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

--

CREATE TABLE
    status (
        id INT PRIMARY KEY AUTO_INCREMENT,
        date_time DATE,
        status INT,
        time_in TIME,
        time_out TIME,
        emp_id INT,
        FOREIGN KEY (emp_id) REFERENCES employees (emp_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

-- For Testing

INSERT INTO
    employees (
        name,
        department,
        address,
        salary,
        gender,
        email,
        phone,
        emp_img,
        admin_id
    )
VALUES (
        'John Doe',
        'Sales',
        '123 Main St, Anytown USA',
        50000.00,
        "male",
        "N/A",
        "N/A",
        "src/main/resources/img/uploads/default-pic.png",
        1
    );

CREATE TABLE
    admin_detail(
        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        picture BLOB,
        name VARCHAR(255),
        email VARCHAR(255),
        phone VARCHAR(255),
        admin_id INT NOT NULL,
        Foreign Key (admin_id) REFERENCES admin (id) ON DELETE CASCADE
    );

-- Test

INSERT INTO admin_detail
VALUES (
        15,
        null,
        "Niraj Maharjan",
        "niraj@maharjan.com",
        9813545029,
        1
    );
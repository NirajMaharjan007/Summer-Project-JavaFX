CREATE DATABASE IF NOT EXISTS my_data ;

use my_data ;

CREATE TABLE
    admin(
        id int PRIMARY KEY NOT NULL,
        name VARCHAR(255),
        password VARCHAR(255)
    );

INSERT INTO admin VALUES ("1","admin", "admin");

INSERT INTO admin VALUES ("2","admin", "");
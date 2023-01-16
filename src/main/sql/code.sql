CREATE SCHEMA online_shop;
USE online_shop;
CREATE TABLE category (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(150)
);
CREATE TABLE product (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(150),
    description VARCHAR(255),
    id_category INT,
    FOREIGN KEY (id_category) REFERENCES category(id)
);
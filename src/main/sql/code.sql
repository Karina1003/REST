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
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category (name) VALUES ('Tools');
INSERT INTO category (name) VALUES ('Furniture');
INSERT INTO category (name) VALUES ('Cutlery');
INSERT INTO product (name, category_id) values ('Screwdriver', 1);
INSERT INTO product (name, category_id) values ('Hammer', 1);
INSERT INTO product (name, category_id) values ('Tape', 1);
INSERT INTO product (name, category_id) values ('Table', 2);
INSERT INTO product (name, category_id) values ('Chair', 2);
INSERT INTO product (name, category_id) values ('Fork', 3);
INSERT INTO product (name, category_id) values ('Knife', 3);
INSERT INTO product (name, category_id) values ('Dish', 3);
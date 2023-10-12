CREATE DATABASE customerdb;
USE customerdb;

DROP TABLE IF EXISTS tblcustomers;
CREATE TABLE tblcustomers(
   customer_id INT PRIMARY KEY,
   customer_name VARCHAR(45) NOT NULL,
   customer_contact VARCHAR(45) NOT NULL,
   customer_email VARCHAR(45) NOT NULL);
- preview of login: one-to-one

DROP TABLE IF EXISTS tbllogins;
CREATE TABLE tbllogins(
   login_id INT PRIMARY KEY,
   login_name VARCHAR(45) NOT NULL,
   customer_id INT,
   CONSTRAINT FK_login_customer
   FOREIGN KEY(customer_id)
   REFERENCES(tblcustomers.customer_id)
   ON DELETE CASCADE
   ON UPDATE CASCADE);


CREATE DATABASE customerdbtest;

USE customerdbtest;

DROP TABLE IF EXISTS tblcustomers;
CREATE TABLE `customerdbtest`.`tblcustomers` (
  `customer_id` INT NOT NULL,
  `customer_name` VARCHAR(45) NULL,
  `customer_contact` VARCHAR(45) NULL,
--  `customer_email` VARCHAR(45) NULL,
--  `customer_login` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  INDEX `customer_login_idx` (`customer_login` ASC) VISIBLE);

DROP TABLE IF EXISTS tblcustomer_logins;
CREATE TABLE `customerdbtest`.`tblcustomer_logins` (
  `login_id` INT NOT NULL,
  `login_name` VARCHAR(45) NULL,
  `login_emailprovider` VARCHAR(30),
  `customer_id` INT NULL);

ALTER TABLE `customerdbtest`.`tblcustomer_logins` 
ADD CONSTRAINT `FK_customer_login`
  FOREIGN KEY (`customer_login`)
  REFERENCES `customerdbtest`.`tblcustomers` (`customer_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `customerdb`.`tblcustomer_logins` 
ADD CONSTRAINT `FK_customer_login`
  FOREIGN KEY (`customer_id`)
  REFERENCES `customerdb`.`tblcustomers` (`customer_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
DROP TABLE IF EXISTS tblevents;
CREATE TABLE `customerdb`.`tblevents` (
  `event_id` INT NOT NULL,
  `event_date` DATE NOT NULL,
  `event_title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`event_id`));
  

DROP TABLE IF EXISTS tblcustomer_event;
CREATE TABLE `customerdb`.`tblcustomer_event` (
  `customer_event_id` INT NOT NULL,
  `customer_event_customer` INT NOT NULL,
  `customer_event_event` INT NOT NULL,
  PRIMARY KEY (`customer_event_id`),
  INDEX `FK_customer_event_customer_idx` (`customer_event_customer` ASC) VISIBLE,
  INDEX `FK_customer_event_event_idx` (`customer_event_event` ASC) VISIBLE,
  CONSTRAINT `FK_customer_event_customer`
    FOREIGN KEY (`customer_event_customer`)
    REFERENCES `customerdb`.`tblcustomers` (`customer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_customer_event_event`
    FOREIGN KEY (`customer_event_event`)
    REFERENCES `customerdb`.`tblevents` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
COMMENT = 'defines the many-to-many-relationship customer-event';

  #EXEC customer_insert @id=1, @Name='Potter', @Loginname='deerexpector', @Contact='howl', @Email='gary.potter@gmail.com'
  
  

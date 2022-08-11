USE mydb;
CREATE TABLE INVENTORIES (
  INVENTORY_ID INT PRIMARY KEY,
  INVENTORY_NAME VARCHAR(100) NOT NULL,
  INVENTORY_PRODUCT INT
);

CREATE TABLE PRODUCTS (
   PRODUCT_ID INT PRIMARY KEY,
   PRODUCT_NAME VARCHAR(100) NOT NULL
);
  
CREATE TABLE BOOKS (
   BOOK_AUTHOR VARCHAR(30) NOT NULL,
   BOOK_ISBN VARCHAR(30) NOT NULL,
   BOOK_PUBLISHER VARCHAR(30) NOT NULL
);

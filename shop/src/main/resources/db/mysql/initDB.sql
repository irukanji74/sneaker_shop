CREATE DATABASE IF NOT EXISTS sneakershop;
USE sneakershop;

CREATE TABLE IF NOT EXISTS  customers (
  id         INT(4)      UNSIGNED NOT NULL PRIMARY KEY ,
  name       VARCHAR(30),
  lastname   VARCHAR(30),
  email      VARCHAR(30),
  password   VARCHAR(100),
  address    VARCHAR(30),
  city       VARCHAR(30),
  zip        VARCHAR(5),
  telephone  VARCHAR(30),
  enabled    BOOLEAN DEFAULT TRUE,
  registered TIMESTAMP,
  cart_id   INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (cart_id) REFERENCES shopping_carts (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS  customer_authorities(
  customer_id  INT(4) not null,
  role		   VARCHAR(30),
  FOREIGN KEY (customer_id) REFERENCES customers (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS shopping_carts(
  id            INT(4) UNSIGNED NOT NULL PRIMARY KEY 
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS verification_token(
  id            INT(4) UNSIGNED NOT NULL PRIMARY KEY,
  token         VARCHAR(30),
  customer_id   INT(4) UNSIGNED NOT NULL,
  expiry_date   TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customers (id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS items(
  id            INT(4) UNSIGNED NOT NULL PRIMARY KEY ,
  name          varchar(255) not null, 
  description   varchar(255), 
  item_price    varchar(255), 
  total_quantity INT(4), 
  sex           varchar(255), 
  item_size     varchar(255), 
  clothes_type  varchar(255), 
  vendor_code   varchar(255),
  cart_id       INT(4) UNSIGNED NOT NULL, 
  FOREIGN KEY (cart_id) REFERENCES shopping_carts (id) ON DELETE CASCADE
)engine=InnoDB;


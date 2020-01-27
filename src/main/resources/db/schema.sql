-- Moved to the quest's content
-- CREATE DATABASE IF NOT EXISTS springboot_bankzecure CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- CREATE USER bankzecure@localhost IDENTIFIED BY 'Ultr4B4nk@L0nd0n';
-- GRANT ALL PRIVILEGES ON springboot_bankzecure.* TO bankzecure@localhost;

CREATE TABLE IF NOT EXISTS customer (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  identifier VARCHAR(6),
  first_name VARCHAR(40),
  last_name VARCHAR(40),
  email VARCHAR(80),
  password VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS credit_card (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id INTEGER NOT NULL,
  type ENUM('MasterCard','Visa'),
  number VARCHAR(16),
  cvv VARCHAR(3),
  expiry VARCHAR(7)
);

ALTER TABLE credit_card ADD CONSTRAINT fk_credit_card_customer_1 FOREIGN KEY (customer_id) REFERENCES customer(id);

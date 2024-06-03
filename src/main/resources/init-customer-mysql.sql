create database if not exists tinybank_customer;

USE tinybank_customer;

CREATE TABLE if not exists customer (
    person_id INT auto_increment PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    active boolean
 );
 
insert into customer (first_name, last_name, active) VALUES ( 'Arthur', 'Gu', true);
insert into customer (first_name, last_name, active) VALUES ( 'Michael', 'Zhang', true);
 
-- CREATE USER 'tester'@'localhost' IDENTIFIED BY 'tester';

GRANT ALL PRIVILEGES ON tinybank_customer.* TO 'tester'@'%';

FLUSH PRIVILEGES;
 
 
 
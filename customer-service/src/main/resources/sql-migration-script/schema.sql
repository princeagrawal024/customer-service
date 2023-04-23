--Customer data may include PII info (e.g., DOB, SSN, Address, Phone, etc.)
--DROP TABLE IF EXISTS CUSTOMER;  

CREATE TABLE IF NOT EXISTS CUSTOMER (
id INT AUTO_INCREMENT PRIMARY KEY,  
first_name varchar(255) NOT NULL,
last_name varchar(255),
address varchar,
date_of_birth varchar,
ssn varchar
);



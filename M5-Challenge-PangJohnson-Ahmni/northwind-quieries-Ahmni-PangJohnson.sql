-- Categories of Produts
USE northwind;
SELECT category FROM products;

-- Products made by Dell
Use northwind;
SELECT * FROM products WHERE product_name LIKE 'Dell %';

-- List orders shipped to Pennsylvania
USE northwind;
SELECT * FROM orders WHERE ship_city = 'Pennsylvania';

-- List first name and last name of all employees with last names that start with letter W
USE northwind;
SELECT first_name, last_name  FROM employees WHERE last_name LIKE 'W%';

-- List all customers from zip codes that start with 55
USE northwind;
SELECT * FROM customers WHERE postal_code LIKE '55%';

-- List all customers from zip codes that end with 0
USE northwind;
SELECT * FROM customers WHERE postal_code LIKE '%0';

-- List first name, .. with .org email
USE northwind;
SELECT first_name, last_name, email FROM customers WHERE email LIKE '%.org';

-- List first name, .. from 202 area code
USE northwind;
SELECT first_name, last_name, email, phone  FROM customers WHERE phone LIKE '1-(202)%';

-- List first name, .. from 202 area code ordered by last, first name
USE northwind;
SELECT first_name, last_name, email, phone FROM customers WHERE phone LIKE '1-(202)%' ORDER BY last_name, first_name;
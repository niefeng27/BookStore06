-- 创建users表
CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(100) NOT NULL UNIQUE,
PASSWORD VARCHAR(100) NOT NULL,
email VARCHAR(100)
)

-- 创建books表
CREATE TABLE books(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
price DOUBLE(11,2) NOT NULL,
sales INT,
stock INT,
img_path VARCHAR(100)
)

-- 创建orders表
CREATE TABLE orders(
id VARCHAR(100) PRIMARY KEY,
order_time DATETIME NOT NULL,
total_count INT NOT NULL,
total_amount DOUBLE(11,2) NOT NULL,
state INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY(user_id) REFERENCES users(id)
)

-- 创建order_items表
CREATE TABLE order_items(
id INT PRIMARY KEY AUTO_INCREMENT,
COUNT INT NOT NULL,
amount DOUBLE(11,2) NOT NULL,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
price DOUBLE(11,2) NOT NULL,
img_path VARCHAR(100) NOT NULL,
order_id VARCHAR(100) NOT NULL,
FOREIGN KEY(order_id) REFERENCES orders(id)
)
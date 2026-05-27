CREATE DATABASE IF NOT EXISTS ctrl_sched;

USE ctrl_sched;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    admin INT DEFAULT 0
);

INSERT INTO users (email, password, admin)
VALUES ('admin@gmail.com', 'admin123', 1);
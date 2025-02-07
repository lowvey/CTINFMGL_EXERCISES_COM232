CREATE DATABASE pinoyflix;

USE pinoyflix;

CREATE TABLE users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);

INSERT INTO users (UserName, Password) VALUES
('admin', '123');

SELECT * FROM users;

CREATE DATABASE WCDEdunextDB;
GO
USE WCDEdunextDB;
GO

-- Tạo bảng Publishers
CREATE TABLE Publishers (
    publisherId INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(40) NOT NULL,
    website VARCHAR(100) NULL
);

-- Tạo bảng Books
CREATE TABLE Books (
    bookId INT PRIMARY KEY IDENTITY(1,1),
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    edition INT NOT NULL,
    publisherId INT NULL,
    genre VARCHAR(100) NULL,
    photo VARCHAR(255) NOT NULL,
    FOREIGN KEY (publisherId) REFERENCES Publishers(publisherId)
);

-- Tạo bảng Roles
CREATE TABLE Roles (
    roleId INT PRIMARY KEY IDENTITY(1,1),
    roleName VARCHAR(255) NOT NULL
);

-- Tạo bảng Users
CREATE TABLE Users (
    userId VARCHAR(20) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    photo VARCHAR(150) NULL,
    roleId INT NULL,
    FOREIGN KEY (roleId) REFERENCES Roles(roleId) ON DELETE SET NULL
);


-- Tạo bảng Reviews
CREATE TABLE Reviews (
    reviewId INT PRIMARY KEY IDENTITY(1,1),
    bookId INT NOT NULL,
    userId VARCHAR(20) NOT NULL,
    rating INT NULL,
    reviewContent TEXT NULL,
    reviewDate DATETIME NOT NULL,
    FOREIGN KEY (bookId) REFERENCES Books(bookId) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE
);

----------------------------------------------------

-- Thêm dữ liệu vào bảng Publishers
INSERT INTO Publishers (name, email, website) VALUES
('Pearson', 'contact@pearson.com', 'https://www.pearson.com'),
('O''Reilly Media', 'info@oreilly.com', 'https://www.oreilly.com'),
('HarperCollins', 'support@harpercollins.com', 'https://www.harpercollins.com');

-- Thêm dữ liệu vào bảng Books
INSERT INTO Books (title, author, edition, publisherId, genre, photo) VALUES
('Clean Code', 'Robert C. Martin', 1, 2, 'Programming', 'cleancode.jpg'),
('The Pragmatic Programmer', 'Andrew Hunt & David Thomas', 2, 2, 'Programming', 'pragmatic.jpg'),
('To Kill a Mockingbird', 'Harper Lee', 1, 3, 'Fiction', 'mockingbird.jpg'),
('Design Patterns', 'Erich Gamma', 1, 1, 'Software Engineering', 'designpatterns.jpg'),
('Effective Java', 'Joshua Bloch', 3, 1, 'Programming', 'effectivejava.jpg');

-- Thêm dữ liệu vào bảng Roles
INSERT INTO Roles (roleName) VALUES
('Admin'),
('Customer'),
('Editor');

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (userId, username, email, photo, roleId) VALUES
('U001', 'admin123', 'admin@example.com', 'admin.jpg', 1),
('U002', 'johndoe', 'johndoe@gmail.com', 'johndoe.jpg', 2),
('U003', 'janedoe', 'janedoe@yahoo.com', 'janedoe.jpg', 2),
('U004', 'editorX', 'editor@example.com', 'editor.jpg', 3),
('U005', 'alice_w', 'alice@gmail.com', 'alice.jpg', 2);

-- Thêm dữ liệu vào bảng Reviews
INSERT INTO Reviews (bookId, userId, rating, reviewContent, reviewDate) VALUES
(1, 'U002', 5, 'A must-read for any programmer.', '2025-03-10'),
(2, 'U003', 4, 'Great insights, but a bit outdated.', '2025-03-11'),
(3, 'U002', 5, 'A timeless classic!', '2025-03-12'),
(4, 'U004', 4, 'Very useful for software architects.', '2025-03-12'),
(5, 'U005', 5, 'One of the best Java books ever written.', '2025-03-13');
----------------------------
USE WCDEdunextDB;
ALTER TABLE Users 
ADD password NVARCHAR(255);
UPDATE Users 
SET password = '123';

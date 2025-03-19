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
    reviewDate DATETIME DEFAULT GETDATE(),
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
(4, 'U004', 4, 'Very useful for software architects.', '2025-03-12');

INSERT INTO Reviews (bookId, userId, rating, reviewContent, reviewDate) VALUES
(1, 'U002', 5, 'An excellent book! Highly recommended.', '2025-03-14'),
(1, 'U003', 4, 'Very informative and well-structured.', '2025-03-14'),
(1, 'U004', 5, 'A must-read for developers.', '2025-03-15'),
(1, 'U005', 3, 'Good content but a bit lengthy.', '2025-03-15'),
(1, 'U002', 4, 'Useful insights and practical examples.', '2025-03-16'),
(1, 'U003', 5, 'One of the best programming books!', '2025-03-16'),
(1, 'U004', 4, 'Clear explanations and great depth.', '2025-03-17'),
(1, 'U005', 5, 'Absolutely loved this book!', '2025-03-17'),
(1, 'U002', 3, 'Some chapters were hard to follow.', '2025-03-18'),
(1, 'U003', 4, 'Solid content, but not for beginners.', '2025-03-18'),
(1, 'U004', 5, 'Changed my perspective on coding.', '2025-02-19'),
(1, 'U005', 4, 'A great reference for professionals.', '2025-02-19'),
(1, 'U002', 5, 'Engaging and insightful read.', '2025-02-20'),
(1, 'U003', 4, 'Good book, but expected more depth.', '2025-02-20'),
(1, 'U004', 3, 'Helpful, but some parts were repetitive.', '2025-02-21'),
(1, 'U005', 5, 'This book is a game changer!', '2025-02-21'),
(1, 'U002', 4, 'Enjoyed the practical examples.', '2025-02-22'),
(1, 'U003', 5, 'Well-written and very insightful.', '2025-02-22'),
(1, 'U004', 4, 'A great addition to my library.', '2025-02-23'),
(1, 'U005', 5, 'Highly useful for software engineers.', '2025-02-23');


----------------------------

ALTER TABLE Users 
ADD password NVARCHAR(255);
UPDATE Users 
SET password = '123';

--------------------------

--------------Phan them 18/3-----------

INSERT INTO Books (title, author, edition, publisherId, genre, photo) VALUES
('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 2, 1, 'Software Engineering', 'bookd1.jpg'),
('Refactoring', 'Martin Fowler', 2, 2, 'Programming', 'bookd2.jpg'),
('Code Complete', 'Steve McConnell', 2, 1, 'Programming', 'bookd3.jpg'),
('You Donot Know JS', 'Kyle Simpson', 1, 2, 'Programming', 'bookd4.jpg'),
('Introduction to the Theory of Computation', 'Michael Sipser', 3, 1, 'Computer Science', 'bookd5.jpg'),
('Artificial Intelligence: A Modern Approach', 'Stuart Russell & Peter Norvig', 4, 1, 'AI', 'bookd6.jpg'),
('Deep Learning', 'Ian Goodfellow, Yoshua Bengio & Aaron Courville', 1, 1, 'AI', 'bookd7.jpg'),
('Python Crash Course', 'Eric Matthes', 2, 2, 'Programming', 'bookd8.jpg'),
('Eloquent JavaScript', 'Marijn Haverbeke', 3, 2, 'Programming', 'bookd9.jpg'),
('The Art of Computer Programming', 'Donald Knuth', 3, 1, 'Computer Science', 'bookd10.jpg'),
('Head First Design Patterns', 'Eric Freeman & Elisabeth Robson', 2, 2, 'Software Engineering', 'bookd.jpg'),
('JavaScript: The Good Parts', 'Douglas Crockford', 1, 2, 'Programming', 'bookd1.jpg'),
('The Phoenix Project', 'Gene Kim, Kevin Behr, George Spafford', 1, 3, 'IT Management', 'bookd2.jpg'),
('The DevOps Handbook', 'Gene Kim, Jez Humble, Patrick Debois, John Willis', 1, 3, 'DevOps', 'bookd3.jpg'),
('Continuous Delivery', 'Jez Humble & David Farley', 1, 2, 'Software Engineering', 'bookd4.jpg'),
('The Algorithm Design Manual', 'Steven Skiena', 2, 1, 'Computer Science', 'bookd5.jpg'),
('Grokking Algorithms', 'Aditya Bhargava', 1, 2, 'Computer Science', 'bookd6.jpg'),
('The Lean Startup', 'Eric Ries', 1, 3, 'Business', 'bookd.jpg'),
('Cracking the Coding Interview', 'Gayle Laakmann McDowell', 6, 2, 'Programming', 'bookd7.jpg'),
('Designing Data-Intensive Applications', 'Martin Kleppmann', 1, 1, 'Data Science', 'bookd8.jpg');

ALTER TABLE Books 
ADD description NVARCHAR(MAX);
UPDATE Books
SET description = 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.'


INSERT INTO Reviews (bookId, userId, rating, reviewContent) VALUES
(1, 'U001', 5, 'A fantastic read with deep insights.'),
(1, 'U002', 4, 'Very helpful for understanding key concepts.'),
(1, 'U003', 3, 'A bit difficult to follow in some parts.'),
(1, 'U004', 5, 'Highly recommended for professionals.'),
(1, 'U005', 2, 'Too technical for beginners.'),
(2, 'U001', 4, 'Clear and well-structured content.'),
(2, 'U002', 5, 'A must-read for software developers.'),
(2, 'U003', 3, 'Good, but expected more real-world examples.'),
(2, 'U004', 5, 'In-depth explanations and useful examples.'),
(2, 'U005', 4, 'A solid foundation for understanding algorithms.'),
(3, 'U001', 2, 'Found it quite boring and repetitive.'),
(3, 'U002', 5, 'Loved the storytelling approach!'),
(3, 'U003', 4, 'A well-written book with practical lessons.'),
(3, 'U004', 3, 'Good, but not groundbreaking.'),
(3, 'U005', 4, 'Helpful for beginners in the field.'),
(4, 'U001', 5, 'A classic book on software engineering.'),
(4, 'U002', 3, 'Too theoretical, I prefer hands-on examples.'),
(4, 'U003', 4, 'Great book with deep insights.'),
(4, 'U004', 5, 'Highly recommended for all developers.'),
(4, 'U005', 4, 'A detailed and informative read.'),
(5, 'U001', 3, 'Good information, but lacks examples.'),
(5, 'U002', 5, 'One of the best books on programming!'),
(5, 'U003', 4, 'Well-structured and easy to understand.'),
(5, 'U004', 2, 'Not what I expected, too abstract.'),
(5, 'U005', 5, 'Practical and insightful, a must-read!'),
(6, 'U001', 4, 'Great explanations with real-world applications.'),
(6, 'U002', 3, 'Some chapters were a bit dry.'),
(6, 'U003', 5, 'An essential book for software architects.'),
(6, 'U004', 4, 'Good book with useful insights.'),
(6, 'U005', 2, 'Not engaging enough for me.'),
(7, 'U001', 5, 'A deep dive into AI and its applications.'),
(7, 'U002', 4, 'Well-researched and informative.'),
(7, 'U003', 3, 'Expected more practical examples.'),
(7, 'U004', 5, 'Great for understanding AI concepts.'),
(7, 'U005', 4, 'A must-read for AI enthusiasts.'),
(8, 'U001', 3, 'Interesting but a bit outdated.'),
(8, 'U002', 5, 'Fantastic book for learning JavaScript.'),
(8, 'U003', 4, 'Detailed and well-structured.'),
(8, 'U004', 5, 'A gem for JavaScript developers.'),
(8, 'U005', 2, 'Did not find it very useful.'),
(9, 'U001', 4, 'Good book with well-explained concepts.'),
(9, 'U002', 3, 'Some topics are too complex.'),
(9, 'U003', 5, 'Excellent resource for developers.'),
(9, 'U004', 4, 'Very practical and easy to understand.'),
(9, 'U005', 5, 'Helped me improve my coding skills.'),
(10, 'U001', 2, 'Too lengthy and difficult to follow.'),
(10, 'U002', 5, 'An amazing book on software design.'),
(10, 'U003', 4, 'Detailed and covers many useful topics.'),
(10, 'U004', 5, 'A must-read for every developer.'),
(10, 'U005', 3, 'Good, but some parts felt repetitive.');

INSERT INTO Reviews (bookId, userId, rating, reviewContent) VALUES
(1, 'U001', 1, 'Very insightful and well-organized.'),
(1, 'U002', 1, 'One of the best books I have read on coding.'),
(1, 'U003', 1, 'Good, but some concepts were hard to grasp.'),
(1, 'U004', 2, 'A must-read for software engineers.'),
(1, 'U005', 2, 'Practical and well-structured content.'),
(2, 'U001', 5, 'This book changed the way I approach coding.'),
(2, 'U002', 4, 'A great resource for both beginners and experts.'),
(2, 'U003', 3, 'Interesting, but not as in-depth as I expected.'),
(2, 'U004', 5, 'Helped me understand design patterns better.'),
(2, 'U005', 4, 'Detailed and well-written, highly recommended.'),
(3, 'U001', 2, 'A bit too theoretical for my taste.'),
(3, 'U002', 5, 'An engaging read with great storytelling.'),
(3, 'U003', 4, 'Good insights, but some sections were repetitive.'),
(3, 'U004', 3, 'Decent book, but not what I expected.'),
(3, 'U005', 5, 'A fantastic book that kept me hooked till the end.');

INSERT INTO Users (userId, username, email, photo, roleId) VALUES
('U404', 'Anonymous', 'lefthend@lefthend.com', 'default.jpg', 2);
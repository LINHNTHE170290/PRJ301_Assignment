--CREATE DATABASE GrandingManagementSystem;
USE GrandingManagementSystem;
--DROP DATABASE GrandingManagementSystem;

-- Student
CREATE TABLE Students (
	stuid  INT PRIMARY KEY NOT NULL,
    stucode  VARCHAR(100) NOT NULL,
    stuname VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    gender BIT NOT NULL,
    stuemail VARCHAR(100) NOT NULL,
    stuphone VARCHAR(20) NOT NULL
);
GO
CREATE TABLE Users (
    username  VARCHAR(100) PRIMARY KEY NOT NULL,
	password  VARCHAR(100) NOT NULL,
    display VARCHAR(100) NOT NULL,
	stuid INT NOT NULL,
	FOREIGN KEY (stuid) REFERENCES Students(stuid)
);

GO
-- Instructor
CREATE TABLE Instructors (
    iid  INT PRIMARY KEY NOT NULL,
	icode  VARCHAR(100) NOT NULL,
    iname VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    gender BIT NOT NULL,
    iemail VARCHAR(100) NOT NULL,
    iphone VARCHAR(20) NOT NULL
);
GO
-- Admin
CREATE TABLE Admins (
    aid INT PRIMARY KEY NOT NULL,
    aname VARCHAR(100) NOT NULL,
    aemail VARCHAR(100) NOT NULL,
    aphone VARCHAR(20) NOT NULL
);
GO
-- Course
CREATE TABLE Courses (
    cid INT PRIMARY KEY NOT NULL,
	ccode VARCHAR(100) NOT NULL,
    cname VARCHAR(100) NOT NULL
);
GO
-- Group (students studying the same subject, managed by an instructor)
CREATE TABLE Groups (
    gid INT PRIMARY KEY NOT NULL,
    gname VARCHAR(100) NOT NULL,
    cid INT NOT NULL,
    iid INT NOT NULL,
    FOREIGN KEY (cid) REFERENCES Courses(cid),
    FOREIGN KEY (iid) REFERENCES Instructors(iid)
);
GO
-- GroupStudent
CREATE TABLE GroupStudent (
    gid INT NOT NULL,
    stuid INT NOT NULL,
    FOREIGN KEY (gid) REFERENCES Groups(gid),
    FOREIGN KEY (stuid) REFERENCES Students(stuid)
);
GO
-- Test
CREATE TABLE Tests (
    tid INT PRIMARY KEY NOT NULL,
    tname VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    time VARCHAR(100) NOT NULL, 
    knowledge VARCHAR(100) NOT NULL,
    condition VARCHAR(100) NOT NULL,
    cid INT NOT NULL,
    FOREIGN KEY (cid) REFERENCES Courses(cid)
);
GO
-- Value (score of each Test)
CREATE TABLE Points (
    pid INT PRIMARY KEY NOT NULL,
	cid INT NOT NULL,
    tid INT NOT NULL,
    stuid INT NOT NULL,
    score INT NOT NULL,
    percentage FLOAT NOT NULL, -- Percentage of total test score
    FOREIGN KEY (cid) REFERENCES Courses(cid),
    FOREIGN KEY (tid) REFERENCES Tests(tid),
    FOREIGN KEY (stuid) REFERENCES Students(stuid)
);
GO
-- Mark (total of all Tests for each Student)
CREATE TABLE Marks (
    mid INT PRIMARY KEY NOT NULL,
    stuid INT NOT NULL,
    cid INT NOT NULL,
    total INT NOT NULL,
    FOREIGN KEY (stuid) REFERENCES Students(stuid),
    FOREIGN KEY (cid) REFERENCES Courses(cid)
);
GO
-- Status
CREATE TABLE Status (
    staid INT PRIMARY KEY  NOT NULL,
    semester VARCHAR(100) NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    status VARCHAR(100) NOT NULL,
    cid INT NOT NULL,
    gid INT NOT NULL,
    FOREIGN KEY (cid) REFERENCES Courses(cid),
    FOREIGN KEY (gid) REFERENCES Groups(gid)
);
GO
-- Insert data into the Students table
INSERT INTO Students (stuid, stucode, stuname, birthday, gender, stuemail, stuphone)
VALUES
    (1, 'HE16345', N'Nguyen Ngoc Anh', '2002-05-15', '0', 'anhnn@fpt.edu.vn', '0814-456-789'),
    (2, 'HE15990', N'Luong Tuan Phan', '2002-08-25', '1', 'phanlt@fpt.edu.vn', '0987-654-321'),
	(3, 'HE16093', N'Pham Hong Giang', '2001-03-10', '0', 'giangph@fpt.edu.vn', '0346-595-9870'),
	(4, 'HE16047', N'Ngo Thu Thuy', '2002-06-28', '0', 'thuynt@fpt.edu.vn', '0436-563-7862'),
	(5, 'HE15936', N'Lien Ngoc Ba', '2001-12-13', '1', 'baln@fpt.edu.vn', '0156-987-876'),
	(6, 'HE16678', N'Phung Van Tien', '2002-10-11', '1', 'tienpv@fpt.edu.vn', '0324-346-0653');
	

GO
INSERT INTO Users (username, password, display, stuid)
VALUES
    ('user1', 'pa123', N'Nguyen Ngoc Anh', 1),
    ('user2', 'pa456', N'Luong Tuan Phan', 2),
	('user3', 'pa789', N'Pham Hong Giang', 3);
	

GO
-- Insert data into the Instructors table
INSERT INTO Instructors (iid, icode, iname, birthday, gender, iemail, iphone)
VALUES 
    (1, 'I1', N'Hoang Quang Tuan', '1885-05-15', '1', 'tuanhq1@fu.edu.vn', '111-222-3333'),
    (2, 'I2', N'Luong Tra My', '1866-09-21', '0','mylt2@fu.edu.vn', '444-555-6666');

GO
-- Insert data into the Admin table
INSERT INTO Admins(aid, aname, aemail, aphone)
VALUES
    (1, 'Admin 1', 'admin1@fptu.edu.vn', '0457-580-678'),
	(2, 'Admin 2', 'admin2@fptu.edu.vn', '0345-653-595');

GO
-- Insert data into the Courses table
INSERT INTO Courses (cid, ccode, cname)
VALUES
    (56, 'PRJ311','Desktop Java Applications'),
    (99, 'MAD101','Discrete mathematics'),
	(100, 'PRO192', 'Object-Oriented Programming'),
	(125, 'SSG103', 'Communication and In-Group Working Skills'),
	(167, 'JPD113', 'Elementary Japanese 1-A1.1'),
	(170, 'CSD201', 'Data Structures and Algorithms'),
	(188, 'OSG202', 'Operating Systems');

GO
-- Insert data into the Groups table
INSERT INTO Groups (gid, gname, cid, iid)
VALUES
	(1, 'SE1477', 170, 1),
	(2, 'SE1622', 167, 2);

GO
-- Insert data into the GroupStudent table
INSERT INTO GroupStudent (gid, stuid)
VALUES
    (1, 1), 
	(1, 3),
	(1, 4),
	(1, 5),
    (2, 2),
	(2, 4),
	(2, 5),
	(2, 6);
	

GO
-- Insert data into the Test table
INSERT INTO Tests (tid, tname, type, time, knowledge, condition, cid)
VALUES
    (1, 'Assignment 1', 'practice in home', 'at home', 'Lesson 1, 2, 3', '> 0', 170),
    (2, 'Assignment 2', 'practice in home', 'at home', 'Lesson 4, 5, 6', '> 0', 170),
	(3, 'Progress test 1', 'practice test', '60 minute', 'Lesson 7, 8, 9', '> 0', 170),
	(4, 'Progress test 2', 'practice test', '60 minute', 'Lesson 10, 11, 12', '> 0', 170),
	(5, 'Practical Exam', 'test', '90 minute','All lessons', '>= 4', 170),
    (6, 'Final Exam', 'multiple choices', '90 minute', 'All lessons', '>= 4', 170),
    
	(7, 'Participation', 'multiple choices', 'in semester', 'attendance', '> 0', 167),
    (8, 'Progress test 1', 'multiple choices', '30 minute', 'Lesson 1, 2', '> 0', 167),
	(9, 'Progress test 2', 'multiple choices', '30 minute', 'Lesson 3, 4', '> 0', 100),
    (10, 'Mid-term test', 'multiple choices', '60 minute', 'Lesson 5, 6', '>= 4', 100),
	(11, 'FE: Listening', 'listening and multiple choices', '30 minute', 'All lessons', '>= 4', 125),
    (12, 'FE: GVR', 'reading and multiple choices', '60 minute', 'All lessons', '>= 4', 125);


GO
-- Insert data into the Value (vid, cid, tid, stuid, score, percentage)
INSERT INTO Points (pid, cid, tid, stuid, score, percentage)
VALUES
    (1, 170, 1, 1, 5, 0.10),
    (2, 170, 2, 1, 10, 0.10),
    (3, 170, 3, 1, 9.2, 0.10),
    (4, 170, 4, 1, 5, 0.10),
	(5, 170, 5, 1, 9.2, 0.30),
    (6, 170, 6, 1, 5, 0.30),

	(7, 170, 1, 3, 7, 0.10),
    (8, 170, 2, 3, 8, 0.10),
    (9, 170, 3, 3, 8.5, 0.10),
    (10, 170, 4, 3, 8.5, 0.10),
	(11, 170, 5, 3, 8.6, 0.30),
    (12, 170, 6, 3, 7, 0.30),

	(13, 170, 1, 4, 5, 0.10),
    (14, 170, 2, 4, 4, 0.10),
    (15, 170, 3, 4, 6, 0.10),
    (16, 170, 4, 4, 5.5, 0.10),
	(17, 170, 5, 4, 7.4, 0.30),
    (18, 170, 6, 4, 8.2, 0.30),

	(19, 170, 1, 5, 5, 0.10),
    (20, 170, 2, 5, 3.5, 0.10),
    (21, 170, 3, 5, 4, 0.10),
    (22, 170, 4, 5, 5, 0.10),
	(23, 170, 5, 5, 4, 0.30),
    (24, 170, 6, 5, 5, 0.30),

	(25, 167, 1, 2, 5, 0.10),
    (26, 167, 2, 2, 7.5, 0.10),
    (27, 167, 3, 2, 6.5, 0.10),
    (28, 167, 4, 2, 5, 0.30),
	(29, 167, 5, 2, 9.2, 0.10),
    (30, 167, 6, 2, 4.6, 0.30),

	(31, 167, 1, 4, 10, 0.10),
    (32, 167, 2, 4, 7, 0.10),
    (33, 167, 3, 4, 6.2, 0.10),
    (34, 167, 4, 4, 6.8, 0.30),
	(35, 167, 5, 4, 7.4, 0.10),
    (36, 167, 6, 4, 6.8, 0.30),

	(37, 167, 1, 5, 5, 0.10),
    (38, 167, 2, 5, 5, 0.10),
    (39, 167, 3, 5, 4.8, 0.10),
    (40, 167, 4, 5, 3, 0.30),
	(41, 167, 5, 5, 2.2, 0.10),
    (42, 167, 6, 5, 4, 0.30),

	(43, 167, 1, 6, 7, 0.10),
    (44, 167, 2, 6, 5.4, 0.10),
    (45, 167, 3, 6, 6, 0.10),
    (46, 167, 4, 6, 4.8, 0.30),
	(47, 167, 5, 6, 7, 0.10),
    (48, 167, 6, 6, 5.6, 0.30);

GO

-- Insert data into the Mark table
INSERT INTO Marks (mid, stuid, cid, total)
SELECT ROW_NUMBER() OVER (ORDER BY v.stuid, v.cid) AS mid, v.stuid, v.cid, SUM(v.score * v.percentage) AS total
FROM Points v
GROUP BY v.stuid, v.cid;

GO
-- Insert data into the Status table
INSERT INTO Status (staid, semester, startdate, enddate, status, cid, gid)
VALUES
    (1, '2023 Fall', '2023-09-06', '2023-11-8', 'Active', 170, 1),
    (2, '2023 Fall', '2023-09-06', '2023-11-8', 'Active', 167, 2);


SELECT g.gname, s.stucode, s.stuname, s.birthday, s.gender, i.iname FROM Groups g
INNER JOIN GroupStudent gs ON g.gid = gs.gid
INNER JOIN Students  s ON gs.stuid = s.stuid
INNER JOIN Instructors i ON g.iid = i.iid;

SELECT t.tname, p.score, p.percentage
FROM Tests t
JOIN Points p ON t.tid = p.tid
JOIN Students s ON p.stuid = s.stuid
JOIN Courses c ON p.cid = c.cid
WHERE c.ccode = 'JPD113' AND s.stucode = 'HE16047';

SELECT total
FROM Marks
WHERE stuid = (SELECT stuid FROM Students WHERE stucode = 'HE16047')
  AND cid = (SELECT cid FROM Courses WHERE ccode = 'JPD113');
CREATE DATABASE qlsv

USE qlsv

CREATE TABLE DBUser(
Username varchar(20) PRIMARY KEY,
Password varchar(25) NOT NULL,
Fullname nvarchar(35)
)

CREATE TABLE Student(
Id int PRIMARY KEY IDENTITY(1,1),
StudentId varchar(10) UNIQUE NOT NULL,
Name nvarchar(25) NOT NULL,
Age int CHECK(age >= 0 AND age <= 100),
Birthday date,
ClassId int foreign key references Class(Id),
Address nvarchar(100),
Hometown nvarchar(20),
)

CREATE TABLE Subject (
Id int primary key identity(1,1),
Name nvarchar(100) not null,
Credit int CHECK(Credit > 0),
TeacherId int foreign key references Teacher(Id)
)

CREATE TABLE Point (
Id int identity(1,1),
StudentId varchar(10) foreign key references Student(StudentId),
SubjectId int foreign key references Subject(Id),
Point1 float check(Point1 >= 0 and Point1 <=10),
Point2 float check(Point2 >= 0 and Point2 <=10),
PointFinal float check(PointFinal >= 0 and PointFinal <=10),
TotalPoint float check(TotalPoint >= 0 and TotalPoint <=10),
constraint PK_Point_StudentId_SubjectId primary key(StudentId, SubjectId)
)

CREATE TABLE Teacher (
Id int PRIMARY KEY identity(1,1),
Name NVARCHAR(50),
Age int CHECK(Age > 16 AND Age < 100),
Phone VARCHAR(10),
Email VARCHAR(50),
Birthday date,
StartWorking date,
Hometown nvarchar(20)
)

CREATE TABLE Session (
Id int PRIMARY KEY,
StartYear int DEFAULT(2000),
EndYear int,
StuQuantity int DEFAULT(0) CHECK(StuQuantity >= 0),
TeacherId int FOREIGN KEY REFERENCES Teacher(Id),
CONSTRAINT CK_Year CHECK(EndYear > StartYear)
)

CREATE TABLE Department (
Id INT PRIMARY KEY identity(1,1),
Name NVARCHAR(50),
Code VARCHAR(5) UNIQUE NOT NULL,
StuQuantity int DEFAULT(0) CHECK(StuQuantity >= 0),
TeacherId int FOREIGN KEY REFERENCES Teacher(Id)
)

CREATE TABLE Class (
Id int PRIMARY KEY identity(1,1),
SessionId int FOREIGN KEY REFERENCES Session(Id),
DepartmentCode VARCHAR(5) FOREIGN KEY REFERENCES Department(Code),
Name VARCHAR(10) UNIQUE NOT NULL,
StuQuantity int DEFAULT(0) CHECK(StuQuantity >= 0)
)


DROP TABLE Student

INSERT INTO DBUser VALUES('admin1','pw1','Nguyen Van Binh')

SELECT * FROM DBUser

SELECT * FROM Student

SELECT * FROM Subject

SELECT * FROM Point

SELECT * FROM Session

SELECT * FROM Teacher

SELECT * FROM Class

SELECT * FROM Department

USE qlsv

SELECT * FROM Student WHERE Class = 'CT4B'

DELETE FROM Student WHERE Id != 2 AND Id != 3 AND Id !=4

SELECT * FROM DBUser

INSERT INTO Subject(Name) VALUES(N'Lập trình web')
INSERT INTO Subject(Name) VALUES(N'Lập trình android')
INSERT INTO Subject(Name) VALUES(N'Lập trình nhúng')

INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) VALUES('CT040246', 1, 8, 8, 8, 8)
INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) VALUES('CT040246', 2, 7, 7, 7, 7)
INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) VALUES('CT040246', 3, 9, 9, 9, 9)
INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) VALUES('CT040247', 1, 8, 8, 8, 8)
INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) VALUES('CT040247', 2, 6, 6, 6, 6)

INSERT INTO Teacher(Name, Age, Phone, Email) VALUES('Teacher 1', 40, '1234567890', 'teacher1@gmail.com')
INSERT INTO Teacher(Name, Age, Phone, Email) VALUES('Teacher 2', 50, '1234567890', 'teacher2@gmail.com')

INSERT INTO Session(Id, StartYear, EndYear, StuQuantity, TeacherId) VALUES(16, 2019, 2024, 700, 1)
INSERT INTO Session(Id, StartYear, EndYear, StuQuantity, TeacherId) VALUES(17, 2020, 2025, 700, 1)

INSERT INTO Department(Name, Code, StuQuantity, TeacherId) VALUES(N'Điện tử viễn thông', 'DT', 250, 2)

INSERT INTO Class(SessionId, DepartmentCode, Name, StuQuantity) VALUES(17, 'DT', 'DT4A', 80)

SELECT sub.Id, sub.Name, sub.Credit, sub.TeacherId, tea.Name TeacherName FROM Subject sub JOIN Teacher tea ON sub.TeacherId = tea.Id


UPDATE Class SET StuQuantity = 0

ALTER TABLE Student
ADD CONSTRAINT FK_Student_ClassId FOREIGN KEY(ClassId) REFERENCES Class(Id)

ALTER TABLE Student
ADD ClassId int NULL

UPDATE Session SET StuQuantity = 0

UPDATE Point SET StudentId = 'CT040246', SubjectId = 1, Point1 = 7, Point2 = 7, PointFinal = 7, TotalPoint = 7 WHERE StudentId = 'CT040246', SubjectId = 1

UPDATE Class SET StuQuantity = 1 WHERE Name = 'CT4A'
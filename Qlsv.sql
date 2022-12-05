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
Class varchar(6),
Address nvarchar(100),
Hometown nvarchar(20),
Gpa float CHECK(gpa >= 0 and gpa <= 4)
)

DROP TABLE Student

INSERT INTO DBUser VALUES('admin1','pw1','Nguyen Van Binh')

SELECT * FROM DBUser

SELECT * FROM Student

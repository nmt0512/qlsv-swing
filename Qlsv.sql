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
)

create table Subject (
Id int primary key identity(1,1),
Name nvarchar(100) not null,
Credit int CHECK(Credit > 0)
)

create table Point (
Id int identity(1,1),
StudentId varchar(10) foreign key references Student(StudentId),
SubjectId int foreign key references Subject(Id),
Point1 float check(Point1 >= 0 and Point1 <=10),
Point2 float check(Point2 >= 0 and Point2 <=10),
PointFinal float check(PointFinal >= 0 and PointFinal <=10),
TotalPoint float check(TotalPoint >= 0 and TotalPoint <=10),
constraint PK_Point_StudentId_SubjectId primary key(StudentId, SubjectId)
)

DROP TABLE Student

INSERT INTO DBUser VALUES('admin1','pw1','Nguyen Van Binh')

SELECT * FROM DBUser

SELECT * FROM Student

SELECT * FROM Subject

SELECT * FROM Point

USE qlsv

SELECT * FROM Student WHERE Class = 'CT4B'

DELETE FROM Student WHERE Id != 2 AND Id != 3 AND Id !=4

SELECT * FROM DBUser

insert into Subject(Name) values(N'Lập trình web')
insert into Subject(Name) values(N'Lập trình android')
insert into Subject(Name) values(N'Lập trình nhúng')

insert into Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) values('CT040246', 1, 8, 8, 8, 8)
insert into Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) values('CT040246', 2, 7, 7, 7, 7)
insert into Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) values('CT040246', 3, 9, 9, 9, 9)
insert into Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) values('CT040247', 1, 8, 8, 8, 8)
insert into Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) values('CT040247', 2, 6, 6, 6, 6)

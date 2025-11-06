create schema school;

use school;

create table student (
ID varchar(10) primary key,
fname varchar(25),
mname varchar(25),
lname varchar(25),
address varchar(75),
zipcode varchar(6),
foreign key (zipcode) references zip(zipcode)
);

create table course (
department varchar(4),
num varchar(4),
description blob,
primary key (department, num)); 

create table building (
code varchar(3) primary key,
name varchar(50));

create table semester (
semID int primary key,
name varchar(10),
year int);

create table class (
id int primary key,
semID int,
department varchar(4),
num varchar(4),
section int,
instructor int,
foreign key (instructor) references faculty(id),
foreign key (semID) references semester(semID), 
foreign key (department, num) references course(department,num) 
);

create table faculty (
id int,
fname varchar(25),
lname varchar(25),
degreeLevel int,
primary key (id)
);

create table studentClass (
stuID varchar(10),
classID int,
grade float,
foreign key (stuID) references student(ID),
foreign key (classID) references class(id),
primary key (stuID, classID)
);

create table zip (
zipcode varchar(6) primary key,
city varchar(25),
state varchar(25),
country varchar(50)
);

insert into zip values ('75701','Tyler','Texas','USA');
insert into zip values ('75703','Tyler','Texas','USA');
insert into zip values ('75766','Jacksonville','Texas','USA');
insert into zip values ('75789','Troup','Texas','USA');
insert into zip values ('75786', 'Troup', 'Texas', 'USA');
insert into zip values ('75150', 'Mesquite', 'Texas', 'USA');


select zipcode from zip;
select * from zip;

insert into student values(101,'Daniel','Clayton','Morris','102 E Commerce St.','75766');
insert into student values(102,'Aurora','Shannon','Bulcock','3088 Old Omen Rd.','75701');
insert into student values(103,'Frank','Paul','Jones','2334 New York Ave.','75703');
insert into student values(104,'Misty','Delilah','Smith','1000 W 5th St.','75150');
insert into student values(105,'Norman','Kyle','Brown','505 Liberty Ln.','75789');


insert into faculty values(001,'Stephen','Rainwater','3');
insert into faculty values(002,'Sean','Butler','1');
insert into faculty values(003,'Daniel','Morris','3');
insert into faculty values(004,'Colleen','Hanratty','2');
insert into faculty values(005,'Jae','Jerkins','1');


insert into building values('COB','Soules College of Business');
insert into building values('RBN','Ratliff Building North');
insert into building values('RBS','Ratliff Building South');
insert into building values('CAS','College of Arts & Sciences');
insert into building values('BEP','Building of Education & Psychology');


insert into semester values(192,'Fall','2023');
insert into semester values(193,'Spring','2024');
insert into semester values(194,'Summer 1','2024');
insert into semester values(195,'Summer 2','2024');
insert into semester values(196,'Fall','2024');


insert into course values('COSC','1315','An introduction to programming using Java (For NON-CS majors)');
insert into course values('COSC','2336','An introduction to data orientated programming');
insert into course values('COSC','1337','An introduction to programming using Java (For CS majors)');
insert into course values('ANTH','2346','An introduction to anthropology, including the 4 subsections of anthropology');
insert into course values('POLS','2305','An introduction to federal government');

insert into class values(192001,192,'COSC','1315',001,001);
insert into class values(192002,192,'COSC','2336',001,003);
insert into class values(193001,193,'COSC','1315',002,001);
insert into class values(193002,193,'COSC','2336',002,003);
insert into class values(194001,196,'COSC','1315',003,001);


insert into studentclass values(101,192001,95);
insert into studentclass values(102,192002,92);
insert into studentclass values(103,193001,87);
insert into studentclass values(104,193002,84);
insert into studentclass values(105,194001,78);





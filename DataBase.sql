DROP DATABASE SERVICE_NEST;
CREATE DATABASE SERVICE_NEST;
use service_nest;
-- select * from Books;
-- DELETE FROM Tutors WHERE ID = '3118';
--     select * from Teaches where ID = 3118;
-- select * from books;
-- select * from Service_Provider where Service_Category='7';
-- select * from keeps where Book_Name = "marigold";
--  select * from Works_In where ID =3118;
 
Create Table `Service_Provider` (
  `ID` int PRIMARY KEY,
  `Full_Name` varchar(255) Not Null,
  `Age` int Not Null,
  `Gender` char Not Null,
  `Address` varchar(255) Not Null,
  `Phone_Number` bigint Not Null,	
  `Ratings` float Not Null,
  `Service_Category` int Not Null,
  `League` int Not Null,
  `Email` varchar(255),
  `Availability` boolean,
  check(Gender in ('M', 'F', 'O')),
  check(Age >= 18),
  check(League in (1, 2, 3)),
  check(Phone_Number >= 100000000 AND Phone_Number<=9999999999)
  );
CREATE TABLE `Location`(
`Location` varchar(255) Primary Key
);
CREATE TABLE `Works_in`(
`ID` int,
`Location` varchar(255),
FOREIGN KEY (ID) REFERENCES Service_Provider(ID),
FOREIGN KEY (Location) REFERENCES Location(Location)
);



CREATE TABLE `Doctors` (
  `ID` int PRIMARY KEY,
  `Clinic_Address` varchar(255) Not Null,
  `Experience` int Not Null,
  `Fees_Per_Visit` int Not Null,
  `Speciality` varchar(255) Not Null
);

CREATE TABLE `Nurses` (
`ID` int PRIMARY KEY,
 `Clinic_Address` varchar(255) Not Null,
  `Experience` int Not Null,
  `Fees_Per_Visit` int Not Null,
  `Speciality` varchar(255) Not Null
);



CREATE TABLE `Laboratories` (
  `ID` int PRIMARY KEY,
  `Lab_Name` varchar(255),
  `Timing_Morning` varchar(255),
  `Timing_Evening` varchar(255)
);
CREATE TABLE `Tests` (
  
  `Test_Name` varchar(255) Primary Key,
  `Cost` int,
  `Requirements` varchar(255),
  `Expected_Report_Date` varchar(255),
  `Room_Number` int
);
CREATE TABLE `Takes`(
`ID` int,
`Test_Name` varchar(255),
FOREIGN KEY (ID) REFERENCES Service_Provider(ID),
FOREIGN KEY (Test_Name) REFERENCES Tests(Test_Name)
);



CREATE TABLE `Plumbers` (
  `ID` int PRIMARY KEY,
  `Fees_Per_Visit` int,
  `Charges_As_Per_Work` int,
  `Experience` int
 
);
CREATE TABLE `Electrician` (
  `ID` int PRIMARY KEY,
  `Fees_Per_Visit` int,
  `Charges_As_Per_Work` int,
  `Experience` int
 
);
CREATE TABLE `HouseKeeping` (
  `ID` int PRIMARY KEY,
  `Fees_Per_Visit` int,
  `Charges_As_Per_Work` int,
  `Experience` int
);


CREATE TABLE `Customers` (
  `ID` int PRIMARY KEY,
  `Full_Name` varchar(255),
  `Age` int,
  `Gender` char,
  `Phone_Number` bigint,
  `Home_Address` varchar(255),
  `Location` varchar(255),
   `Email` varchar(255),
   check(Gender in ('M', 'F', 'O')),
   check(Location in ('North','East','South','West','Central')),
   check(Age >= 18),
   check(Phone_Number >= 100000000 AND Phone_Number<=9999999999)
 
  #`Ratings` float
);

CREATE TABLE `Tutors` (
  `ID` int PRIMARY KEY,
  `Fees_Per_Month` int,
  `Fees_Per_visit` int,
  `Experience` int,
  `Qualification` varchar(255)
);
CREATE TABLE `Subjects`(
`Sub` varchar(255) Primary Key
);
CREATE TABLE `Teaches`(
`ID` int,
`Sub` varchar(255),
CONSTRAINT FK_ID FOREIGN KEY (ID) REFERENCES Service_Provider(ID),
FOREIGN KEY (Sub) REFERENCES Subjects(Sub)
);


CREATE TABLE `BookStores` (
  `ID` int PRIMARY KEY,
  `Location` varchar(255),
  `Second_Hand_Books` varchar(255),
  `Stationary_Available` varchar(255),
  `Donations_Accepted` varchar(255),
  `Timings` varchar(255)
);
CREATE TABLE `Books`(
`Book_Name` varchar(255) unique
);
CREATE TABLE `Keeps`(
`store_ID` int,
`Book_Name` varchar(255),
FOREIGN KEY (store_ID) REFERENCES BookStores(ID)
);


CREATE TABLE `Services` (
  `Services_ID` int PRIMARY KEY,
  `Customer_Id` int,
  `Customer_Phone` bigint,
  `Service_Provider_Phone` bigint,
  `Service_Provider_Id` int,
  `Amount` int,
  `Review_By_Customer` int,
  `Progress` varchar(255),
  `Service_Category` int,
  `Location` varchar(255),
  `Home_Address` varchar(255),
  `Date_Time_Of_Service` TimeStamp,
  FOREIGN KEY (Customer_ID) REFERENCES Customers(ID),
  FOREIGN KEY (Service_Provider_Id) REFERENCES Service_Provider(ID),
  check(Progress in ('Pending', 'Completed', 'Rated'))
);

CREATE INDEX index_ID ON Service_Provider(ID);
CREATE INDEX index_ServiceID ON Services(Services_ID);
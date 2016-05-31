CREATE DATABASE IF NOT EXISTS railage;
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON railage.* TO 'admin'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

USE railage;
CREATE TABLE IF NOT EXISTS train(
   trainId INT AUTO_INCREMENT
   ,trainNumber VARCHAR(6) NOT NULL
   ,numberOfSeats INT NOT NULL
   ,PRIMARY KEY (trainId)
 ); 
 
 CREATE TABLE IF NOT EXISTS station(
   stationId INT AUTO_INCREMENT
   ,stationName VARCHAR(200) NOT NULL
   ,PRIMARY KEY (stationId)
 );
 
 CREATE TABLE IF NOT EXISTS timetable(
   timetableId INT AUTO_INCREMENT
   ,trainId INT NOT NULL
   ,stationId INT NOT NULL
   ,arrivalTime TIME NOT NULL
   ,departureTime TIME NOT NULL
   ,PRIMARY KEY (timetableId)
   ,FOREIGN KEY (trainId) REFERENCES train (trainId)
   ,FOREIGN KEY (stationId) REFERENCES station (stationId)
);

CREATE TABLE IF NOT EXISTS passenger(
   passengerId INT AUTO_INCREMENT
   ,firstName VARCHAR(100) NOT NULL
   ,lastName VARCHAR(200) NOT NULL
   ,dateOfBirth DATE NOT NULL
   ,login VARCHAR(200) DEFAULT NULL
   ,email VARCHAR(200) DEFAULT NULL
   ,pass VARCHAR(100) DEFAULT NULL
   ,passengerType TINYINT(1) DEFAULT 0 #0-default; 1-for employees 
   ,PRIMARY KEY (passengerId)
 );
 
 CREATE TABLE IF NOT EXISTS ticket(
   ticketId INT AUTO_INCREMENT
   ,passengerId INT NOT NULL
   ,trainId INT NOT NULL
   ,PRIMARY KEY (ticketId)
   ,FOREIGN KEY (passengerId) REFERENCES passenger (passengerId)
   ,FOREIGN KEY (trainId) REFERENCES train (trainId)
);
   

  
 

CREATE DATABASE IF NOT EXISTS uberbahn;
CREATE USER IF NOT EXISTS 'uberbahn_webapp'@'localhost' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON uberbahn.* TO 'uberbahn_webapp'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

USE uberbahn;

CREATE TABLE IF NOT EXISTS Route(
	id INT AUTO_INCREMENT
    ,title VARCHAR(100) NOT NULL UNIQUE
    ,timeOfDeparture TIME NOT NULL
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Station(
	id INT AUTO_INCREMENT
    ,title VARCHAR(150) NOT NULL UNIQUE
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Spot(
	stationId INT
    ,routeId INT
    ,timeFromDeparture TIME NOT NULL
    ,PRIMARY KEY (stationId,routeId)
    ,CONSTRAINT fk_spot_station FOREIGN KEY (stationId) REFERENCES Station(id)
    ,CONSTRAINT fk_spot_route FOREIGN KEY (routeId) REFERENCES Route(id)
    ,CONSTRAINT uc_spot_routeTime UNIQUE (routeId, timeFromDeparture)
);

CREATE TABLE IF NOT EXISTS Train(
	id INT AUTO_INCREMENT
    ,routeId INT NOT NULL
    ,dateOfDeparture DATE NOT NULL
    ,numberOfSeats INT NOT NULL
    ,PRIMARY KEY (id)
    ,CONSTRAINT fk_train_route FOREIGN KEY (routeId) REFERENCES Route(id)
    ,CONSTRAINT uc_train_routeDate UNIQUE (routeId, dateOfDeparture)
);

CREATE TABLE IF NOT EXISTS AppUser(
	id INT AUTO_INCREMENT
    ,login VARCHAR(100) NOT NULL
    ,email VARCHAR(255) NOT NULL
    ,pass VARCHAR(100) NOT NULL
    ,firstName VARCHAR(100) NOT NULL
    ,lastName VARCHAR(200) NOT NULL
    ,dateOfBirth DATE NOT NULL
    ,employee BOOLEAN NOT NULL
    ,PRIMARY KEY (id)
    ,CONSTRAINT uc_appUser_login UNIQUE (login)
    ,CONSTRAINT uc_appUser_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Ticket(
	id INT AUTO_INCREMENT
    ,trainId INT NOT NULL
    ,firstName VARCHAR(100) NOT NULL
    ,lastName VARCHAR(200) NOT NULL
    ,dateOfBirth DATE NOT NULL
    ,stationOfDepartureId INT NOT NULL
    ,stationOfArrivalId INT NOT NULL
    ,dateOfPurchase DATETIME NOT NULL
    ,userId INT NOT NULL
    ,PRIMARY KEY (id)
    ,CONSTRAINT fk_ticket_train FOREIGN KEY (trainId) REFERENCES Train(id)
    ,CONSTRAINT fk_ticket_stationOfDeparture FOREIGN KEY (stationOfDepartureId) REFERENCES Station(id)
    ,CONSTRAINT fk_ticket_stationOfArrival FOREIGN KEY (stationOfArrivalId) REFERENCES Station(id)
    ,CONSTRAINT fk_ticket_user FOREIGN KEY (userId) REFERENCES AppUser(id)
);





 
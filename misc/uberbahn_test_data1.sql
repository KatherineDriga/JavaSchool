SELECT * FROM uberbahn.account;

insert into uberbahn.station (title) values ('Moscow');
insert into uberbahn.station (title) values ('St-Petersburg');
insert into uberbahn.station (title) values ('Tver');
insert into uberbahn.station (title) values ('Voronezh');
insert into uberbahn.station (title) values ('Rostov');
insert into uberbahn.station (title) values ('Krasnodar');
insert into uberbahn.station (title) values ('Sochi');


insert into uberbahn.route (title,timeOfDeparture) values ('Moscow-St-Petersburg', '22:00:00');
insert into uberbahn.route (title,timeOfDeparture) values ('St-Petersburg-Moscow', '14:00:00');
insert into uberbahn.route (title,timeOfDeparture) values ('St-Petersburg-Tver-Moscow', '08:20:00');
insert into uberbahn.route (title,timeOfDeparture) values ('St-Petersburg-Sochi', '20:25:00');


insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats) values (1,'2016-06-20',3);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats) values (2,'2016-07-21',3);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats) values (3,'2016-06-20',3);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats) values (4,'2016-07-21',3);


insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (1,1,'00:00:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (1,2,'08:00:00');

insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (2,2,'00:00:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (2,1,'08:10:00');

insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (3,2,'00:00:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (3,3,'06:10:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (3,1,'08:15:00');

insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,2,'00:00:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,3,'06:02:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,1,'08:05:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,4,'17:10:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,5,'29:30:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,6,'42:15:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (4,7,'45:12:00');

insert into uberbahn.account (login, email, secret, firstName, lastName, dateOfBirth, employee)
values ('administrator', 'administrator.example.com', '123','Elena', 'Pavlova', '1985-12-11', true);

insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId)
values ('4', 'Alex', 'Serov', '1987-05-06', 2, 1, '2016-06-05 14:20:00', 1);
insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId)
values ('4', 'Oleg', 'Ivanov', '1988-05-06', 3, 1, '2016-06-05 14:25:00', 1);
insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId)
values ('4', 'Olga', 'Maksimova', '1990-04-02', 2, 1, '2016-06-05 14:20:00', 1);

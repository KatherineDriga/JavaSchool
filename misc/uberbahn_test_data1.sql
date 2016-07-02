
insert into uberbahn.station (title, timezone) values ('Moscow', 3);
insert into uberbahn.station (title, timezone) values ('St-Petersburg', 3);
insert into uberbahn.station (title, timezone) values ('Tver', 3);
insert into uberbahn.station (title, timezone) values ('Voronezh', -1);
insert into uberbahn.station (title, timezone) values ('Rostov', -2);
insert into uberbahn.station (title, timezone) values ('Krasnodar', 3);
insert into uberbahn.station (title, timezone) values ('Sochi', 3);


insert into uberbahn.route (title,timeOfDeparture, pricePerMinute) values ('Moscow-St-Petersburg', '22:00:00', 1.5);
insert into uberbahn.route (title,timeOfDeparture, pricePerMinute) values ('St-Petersburg-Moscow', '14:00:00', 1.5);
insert into uberbahn.route (title,timeOfDeparture, pricePerMinute) values ('St-Petersburg-Tver-Moscow', '00:00:00', 1.4);
insert into uberbahn.route (title,timeOfDeparture, pricePerMinute) values ('St-Petersburg-Sochi', '00:00:00', 2.0);

insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats, priceCoefficient, archived) values (1,'2016-07-05',50, 1.0, 0);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats, priceCoefficient, archived) values (2,'2016-07-05',20, 1.0, 0);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats, priceCoefficient, archived) values (3,'2016-07-05',50, 1.5, 0);
insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats, priceCoefficient, archived) values (4,'2016-07-05',60, 2.0, 0);


insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (1,1, 0);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (1,2, 360);

insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (2,2,0);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (2,1,400);

insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (3,2,0);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (3,3,420);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (3,1,560);

insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,2,0);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,3,360);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,1,600);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,4,1200);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,5,2400);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,6,3100);
insert into uberbahn.spot (routeId,stationId,minutesSinceDeparture) values (4,7,4400);

insert into uberbahn.presence (trainId, spotId, instant, numberOfTickets) values (1, 1, '16-06-30 22:00:00', 1);
insert into uberbahn.presence (trainId, spotId, instant, numberOfTickets) values (1, 2, '16-07-01 04:00:00', 0);
insert into uberbahn.presence (trainId, spotId, instant, numberOfTickets) values (2, 4, '16-06-27 20:00:00', 0);

insert into uberbahn.account (login, email, secret, firstName, lastName, dateOfBirth, employee)
values ('administrator', 'administrator.example.com', '123','Elena', 'Pavlova', '1985-12-11', true);

insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId, price)
values ('1', 'Alex', 'Serov', '1987-05-06', 1, 2, '2016-06-05 14:20:00', 1, 100.0);
insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId, price)
values ('2', 'Oleg', 'Ivanov', '1988-05-06', 1, 2, '2016-06-05 14:25:00', 1, 100.0);
insert into uberbahn.ticket (trainId, firstName, lastName, dateOfBirth, stationOfDepartureId, stationOfArrivalId, datetimeOfPurchase, accountId, price)
values ('4', 'Olga', 'Maksimova', '1990-04-02', 2, 1, '2016-06-05 14:20:00', 1, 300.0);

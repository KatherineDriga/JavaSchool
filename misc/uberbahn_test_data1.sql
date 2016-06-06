SELECT * FROM uberbahn.route;

insert into uberbahn.station (title) values ('Moscow');
insert into uberbahn.station (title) values ('St-Petersburg');

insert into uberbahn.route (title,timeOfDeparture) values ('Moscow-St-Petersburg', '22:00:00');

insert into uberbahn.train (routeId,dateOfDeparture,numberOfSeats) values (1,'2016-06-06',20);

insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (1,1,'00:00:00');
insert into uberbahn.spot (routeId,stationId,timeSinceDeparture) values (1,2,'08:00:00');
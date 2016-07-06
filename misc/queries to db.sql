use uberbahn;
select * from ticket;

select * from train;

select * from spot
where routeId=10;

select * from route;

select * from station;

select * from presence
where trainId=11;

select * from spot
where routeId=4;

select * from station;

select * from account;


select t.id, t.routeId, t.dateOfDeparture, t.numberOfSeats, t.priceCoefficient, t.archived 
from train as t
join presence as p1
on t.id=p1.trainId
join presence as p2
on p1.trainId=p2.trainId
join spot as s1
on s1.id=p1.spotId
join spot as s2
on s2.id=p2.spotId
where s1.stationId=7
and s2.stationId=1
and p1.instant>='2016-07-01'
and p1.instant<'2016-07-22'
and p1.instant<p2.instant;
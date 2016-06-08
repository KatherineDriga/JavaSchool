USE `uberbahn`;
DROP procedure IF EXISTS `getTrainsFromStationAtoStationB`;

DELIMITER $$
USE `uberbahn`$$
CREATE PROCEDURE `getTrainsFromStationAtoStationB` (IN 
stationA varchar(150), stationB varchar (150), timeSince time,
timeUntil time, dateOfDeparture date)
BEGIN
	select t.id as TrainId, r.title as RouteTitle, stationA as StationOfDeparture,
		date_add(dateOfDeparture, interval addtime(r.timeOfDeparture, spot1.timeSinceDeparture) hour_second) as DateTimeOfDeparture,
        stationB as StationOfArrival,
        date_add(dateOfDeparture, interval addtime(r.timeOfDeparture, spot2.timeSinceDeparture) hour_second) as DateTimeOfArrival
	from spot as spot1
	join station as station1
	on spot1.stationId=station1.id
	join spot as spot2
	on spot1.routeId=spot2.routeId
	join station as station2
	on spot2.stationId=station2.id
	join route as r
	on spot1.routeId=r.id
	join train as t 
	on r.id=t.routeId
	where station1.title=stationA
	and station2.title=stationB
	and spot1.timeSinceDeparture < spot2.timeSinceDeparture
	and t.dateOfDeparture=dateOfDeparture
	and (TimeOfDeparture between timeSince and timeUntil)
    order by DateTimeOfDeparture;
END$$

DELIMITER ;

CALL `getTrainsFromStationAtoStationB`('St-Petersburg', 'Moscow', '00:00:00', '23:59:00', '2016-06-07');



USE `uberbahn`;
DROP procedure IF EXISTS `getTrainsForStation`;

DELIMITER $$
USE `uberbahn`$$
CREATE PROCEDURE `getTrainsForStation` (IN
stationA varchar(150), timeSince time, timeUntil time, dateOfDeparture date)
BEGIN
	select t.id as TrainId, r.title as RouteTitle, 
    addtime(r.timeOfDeparture,sp.timeSinceDeparture) as DepartureTime
	from spot as sp
	join station as st
	on sp.stationId=st.id
	join route as r
	on sp.routeId=r.id
	join train as t
	on r.id=t.routeId
	where st.title=StationA
	and t.dateOfDeparture=dateOfDeparture
	and addtime(r.timeOfDeparture,sp.timeSinceDeparture) between timeSince and timeUntil
    order by DepartureTime;
END$$

DELIMITER ;

CALL `getTrainsForStation`('St-Petersburg', '00:00:00', '23:59:00', '2016-06-07');


                                
                                 
select sp.stationId, sp.routeId, count(t.id) 
from spot as sp
left outer join ticket as t
on sp.stationId=t.stationOfDepartureId
where sp.routeId=(select t.routeId 
				  from train as t
                  where t.id=4)
and sp.timeSinceDeparture between
(select sp.timeSinceDeparture 
from spot as sp
where sp.stationId=(select st.id 
			from station as st
            where st.title='St-Petersburg')
and sp.routeId=(select t.routeId 
				from train as t
                where t.id=4))
and (select sp.timeSinceDeparture 
from spot as sp
where sp.stationId=(select st.id 
			from station as st
            where st.title='Sochi'))
group by sp.stationId;

                                 
                           
                                 
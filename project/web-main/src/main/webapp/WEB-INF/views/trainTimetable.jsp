<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12.06.2016
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TrainTimetable</title>
</head>
<body>
<h2>${timetable.stationOfDeparture} - ${timetable.stationOfArrival}</h2><br>
<h2>${since} - ${until}</h2>
<c:choose>
    <c:when test="${empty timetable.scheduleEvents}">
        <h3>No trains found</h3>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Train</th>
                <th>Route Title</th>
                <th>Departs From</th>
                <th>Date Of Departure</th>
                <th>Time Of Departure</th>
                <th>Arrives At</th>
                <th>Date Of Arrival</th>
                <th>Time Of Arrival</th>
            </tr>
            <c:forEach var="train" items="${timetable.scheduleEvents}">
                <tr>
                    <td>${train.trainId}</td>
                    <td>${train.routeTitle}</td>
                    <td>${timetable.stationOfDeparture}</td>
                    <td>${train.dateOfDeparture}</td>
                    <td>${train.timeOfDeparture}</td>
                    <td>${timetable.stationOfArrival}</td>
                    <td>${train.dateOfArrival}</td>
                    <td>${train.timeOfArrival}</td>
                </tr>
            </c:forEach>

        </table>
    </c:otherwise>
</c:choose>
</body>
</html>

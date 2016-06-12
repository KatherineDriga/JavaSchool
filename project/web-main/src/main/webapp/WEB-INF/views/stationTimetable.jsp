<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 09.06.2016
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StationTimetable</title>
</head>
<body>
<h2>${timetable.title}</h2>
<c:choose>
    <c:when test="${empty timetable.scheduleEvents}">
        <h3>No trains found</h3>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Date</th>
                <th>Time On Station</th>
                <th>Route</th>
                <th>Departs from</th>
                <th>Arrives at</th>
                <th>Train</th>
            </tr>
            <c:forEach var="event" items="${timetable.scheduleEvents}">
                <tr>
                    <td>${event.date}</td>
                    <td>${event.time}</td>
                    <td>${event.route}</td>
                    <td>${event.departsFrom}</td>
                    <td>${event.arrivesAt}</td>
                    <td>${event.train}</td>
                </tr>
            </c:forEach>


        </table>
    </c:otherwise>
</c:choose>

</body>
</html>



<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
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
    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

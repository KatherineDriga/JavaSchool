<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
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
    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>



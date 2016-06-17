<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2>Passengers</h2>
    <h2>Train ${trainId}</h2>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
            <th>Station of Departure</th>
            <th>Station of Arrival</th>
        </tr>
        <c:forEach var="passenger" items="${passengerInfos}">
            <tr>
                <td>${passenger.firstName}</td>
                <td>${passenger.lastName}</td>
                <td>${passenger.dateOfBirth}</td>
                <td>${passenger.stationOfDeparture}</td>
                <td>${passenger.stationOfArrival}</td>
            </tr>
        </c:forEach>
    </table>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

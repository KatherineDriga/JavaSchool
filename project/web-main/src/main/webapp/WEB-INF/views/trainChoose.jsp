<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2>Choose train:</h2>
    <table>
        <tr>
            <th></th>
            <th>Train</th>
            <th>Route Title</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Number Of Seats Available</th>
        </tr>
        <c:forEach var="train" items="${trains}" varStatus="status">
        <tr>
            <td><input type="radio" name="radioButton" value="${train.trainId}"/></td>
            <td id = "trainId">${train.trainId}</td>
            <td id = "routeTitle">${train.routeTitle}</td>
            <td>${train.dateOfDeparture}<br />${train.timeOfDeparture}<br />${train.stationOfDeparture}</td>
            <td>${train.dateOfArrival}<br />${train.timeOfArrival}<br />${train.stationOfArrival}</td>
            <td>${train.numberOfSeatsAvailable}</td>
        </tr>
        </c:forEach>
    </table>
    <span id="stationOfDepartureId">${stationOfDepartureId}</span>
    <span id="stationOfArrivalId">${stationOfArrivalId}</span>
    <br>
    <p><input id = "chooseTrain" type="button" value="Choose"></p>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/trainChoose.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

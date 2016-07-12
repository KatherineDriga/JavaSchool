<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
<c:choose>
    <c:when test="${empty trains}">
        <h3>No trains found!</h3>
    </c:when>
    <c:otherwise>
    <h2>Choose train:</h2>
    <div class="scroll-table">
    <table class="table table-striped">
        <tr>
            <th></th>
            <th>Train</th>
            <th>Route Title</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Travel Time</th>
            <th>Number Of Seats <br />Available</th>
            <th>Price</th>
        </tr>
        <c:forEach var="train" items="${trains}">
        <tr>
        <c:choose>
            <c:when test="${train.numberOfSeatsAvailable>0}">
                <td><input type="radio" name="radioButton" value="${train.trainId}"/></td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
            <td id = "trainId">${train.trainId}</td>
            <td id = "routeTitle">${train.routeTitle}</td>
            <td>${train.dateOfDeparture}<br />${train.timeOfDeparture}<br />${train.stationOfDeparture}</td>
            <td>${train.dateOfArrival}<br />${train.timeOfArrival}<br />${train.stationOfArrival}</td>
            <td>${train.travelTime}</td>
            <td>${train.numberOfSeatsAvailable}</td>
            <td>${train.ticketPrice}</td>
        </tr>
        </c:forEach>
    </table>
    </div>
    <span hidden="true" id="stationOfDepartureId">${stationOfDepartureId}</span>
    <span hidden="true" id="stationOfArrivalId">${stationOfArrivalId}</span>

    <br>
    <p><input class="btn btn-success" id = "chooseTrain" type="button" value="Proceed to purchase"></p>
        <div>
            <span id="errorMessage"></span>
        </div>
    </c:otherwise>
</c:choose>
</section>
<script src="static/scripts/listOfTrains.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
<c:choose>
    <c:when test="${not empty exception}">
        <h3>${exception}</h3>
    </c:when>
    <c:otherwise>
    <h2>Add Stations To Route ${routeTitle}</h2>
    <form class="addStationsToRoute">
        <p><label>Station 1</label>
            <select class="stationId">
                <option value="null">Select</option>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.id}">${station.title}</option>
                </c:forEach>
            </select>
            <label> Minutes Since Departure</label>
            <input type="number" class = "minutesSinceDeparture" value="0" readonly/>
        </p>
        <c:forEach var="i" begin="2" end="${numberOfStations}">
            <p><label>Station ${i}</label>
                <select class="stationId">
                    <option value="null">Select</option>
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.id}">${station.title}</option>
                    </c:forEach>
                </select>
                <label> Minutes Since Departure</label>
                <input type="number" class = "minutesSinceDeparture" />
            </p>
        </c:forEach>

        <p><input class="btn btn-success" id = "addRouteButton" type="button" value="Add"></p>
        <div>
            <span id="errorMessage"></span>
        </div>
    </form>
    <span hidden="true" id="timeOfDeparture">${timeOfDeparture}</span>
    <span hidden="true" id="routeTitle">${routeTitle}</span>
    <span hidden="true" id="pricePerMinute">${pricePerMinute}</span>
    </c:otherwise>
</c:choose>
</section>
<script src="static/scripts/addStationsToRouteForm.js"></script>

<%@include file="/WEB-INF/jspf/footer.jspf" %>


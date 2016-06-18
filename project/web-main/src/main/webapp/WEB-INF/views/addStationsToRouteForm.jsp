<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add Stations To Route ${routeTitle}</h2>
    <form class="addStationsToRoute">
        <c:forEach var="i" begin="1" end="${numberOfStations}">
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

        <p><input id = "addRouteButton" type="button" value="Add"></p>
    </form>
    <span id="timeOfDeparture">${timeOfDeparture}</span>
    <span id="routeTitle">${routeTitle}</span>
    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/addStationsToRouteForm.js"></script>

<%@include file="/WEB-INF/jspf/footer.jspf" %>


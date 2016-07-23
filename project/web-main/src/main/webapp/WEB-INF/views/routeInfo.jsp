<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
<c:choose>
    <c:when test="${not empty exception}">
        <h3>${exception}</h3>
    </c:when>
    <c:otherwise>
    <div class="scroll-table">
    <h2 id="routeInfo">Route Information</h2>
    <p>Id: ${route.id}</p>
    <p>Title: ${route.title}</p>
    <p>Time of Departure: ${route.timeOfDeparture}</p>
    <p>Stations: </p>
    <ul>
    <c:forEach var="spot" items="${route.spots}">
        <li>${spot.stationTitle}</li>
    </c:forEach>
    </ul>
    <p>Price per Minute: ${route.pricePerMinute}</p>
    </div>
    </c:otherwise>
</c:choose>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

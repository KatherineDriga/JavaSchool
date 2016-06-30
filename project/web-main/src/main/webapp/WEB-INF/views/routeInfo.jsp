<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2>Route Information</h2>
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



</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

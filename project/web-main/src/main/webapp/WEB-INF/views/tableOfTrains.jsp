<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2>Choose train:</h2>
    <table>
        <tr>
            <th></th>
            <th>Train</th>
            <th>Route Title</th>
            <th>Date of Departure</th>
        </tr>
        <c:forEach var="info" items="${findTrainInfo}">
            <tr>
                <td><input type="radio" name="radioButton" value="${info.id}"/></td>
                <td>${info.id}</td>
                <td>${info.routeTitle}</td>
                <td>${info.dateOfDeparture}</td>
            </tr>
        </c:forEach>
    </table>

    <p><input id = "chooseTrainButton" type="button" value="Choose"></p>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/tableOfTrains.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

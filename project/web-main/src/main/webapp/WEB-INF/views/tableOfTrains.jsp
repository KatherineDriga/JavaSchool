<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
<c:choose>
    <c:when test="${empty findTrainInfo}">
        <h3>No trains found!</h3>
    </c:when>
    <c:otherwise>
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
    <br>
    <p><input class="btn btn-success" id = "chooseTrainButton" type="button" value="Show passengers"></p>
        <div>
            <span id="errorMessage"></span>
        </div>
</c:otherwise>
    </c:choose>
</section>
<script src="/scripts/tableOfTrains.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

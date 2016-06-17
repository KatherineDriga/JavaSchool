
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add train</h2>
    <form class="addTrain" method="post">

        <p><label>Route</label>
            <select id="route">
                <option value="null">Select</option>
                <c:forEach var="route" items="${routes}">
                    <option value="${route.id}">${route.title}</option>
                </c:forEach>
            </select>
        </p>
        <p><label>Date of Departure</label>
            <input type="date" id = "dateOfDeparture" />
        </p>
        <p><label>Number of Seats</label>
            <input type="number" id = "numberOfSeats" />
        </p>


        <input id = "addTrainButton" type="button" value="Add Train">

    </form>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/addTrainForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>



<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Find trains</h2>
    <form class="findTrains">

        <p><label>Route</label>
            <select id="route">
                <option value="null">Select</option>
                <c:forEach var="route" items="${routes}">
                    <option value="${route.id}">${route.title}</option>
                </c:forEach>
            </select>
        </p>


        <input id = "chooseRouteButton" type="button" value="Choose Route">

    </form>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/findTrainForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


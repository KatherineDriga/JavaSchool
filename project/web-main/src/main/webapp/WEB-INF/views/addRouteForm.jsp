
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add route</h2>
    <form class="addRoute">

        <p><label>Route Title</label>
            <input type="text" id="routeTitle"></p>
        <p><label>Number of Stations</label>
            <input type="text" id="numberOfStations"></p>

        <input id = "addStationsButton" type="button" value="Add Stations to Route">

    </form>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/addRouteForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>



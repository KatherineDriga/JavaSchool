
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add route</h2>
    <div style="width: 250px">
    <form role="form" class="addRoute">
        <div class="form-group">
        <p><label>Route Title</label>
            <input class="form-control" type="text" id="routeTitle"></p>
        <p><label>Number of Stations</label>
            <input class="form-control" type="number" id="numberOfStations"></p>
        <p><label>Time of Departure</label>
            <input class="form-control" type="time" id="timeOfDeparture" value="00:00"></p>
        <p><label>Price per Minute</label>
            <input class="form-control" type="number" step="0.01" id="pricePerMinute"></p>

            <input class="btn btn-success" id = "addStationsButton" type="button" value="Add Stations to Route">
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
    </form>
    </div>


</section>
<script src="static/scripts/addRouteForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>



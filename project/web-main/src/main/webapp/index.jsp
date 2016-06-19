
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <div class="wrap">
        <div class="left_col">
            <h3>Clients Information</h3>
        <ul>
            <li><a href="/trainTimetableSearch"><h4>Search for Train & Buy Ticket</h4></a></li>
            <li><a href="/stationTimetableSearch"><h4>Station Timetable</h4></a></li>
        </ul>
        </div>
        <div class="right_col">
            <h3>Employees Information</h3>
        <ul>
            <li><a href="/addStationForm"><h4>Add Station</h4></a></li>
            <li><a href="/addRouteForm"><h4>Add Route</h4></a></li>
            <li><a href="/addTrainForm"><h4>Add Train</h4></a></li>
            <li><a href="/findTrainForm"><h4>Find Train & Registered Passengers</h4></a></li>
        </ul>
        </div>
    </div>

</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

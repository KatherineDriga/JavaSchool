
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Ticket Purchase Form</h2>
    <form class="buy_ticket">
        <div class="wrap">
            <!--<div class="left_col">
                <h3>Route information</h3>
                <p><label class="label">Route Title</label>
                    <input type="text" name="Route Title" value="RouteTitle" readonly></p>
                <p><label class="label">Date of Departure</label>
                    <input type="date" name="dateOfDeparture" value="2016-07-07" readonly></p>
                <p><label class="label">Time of Departure</label>
                    <input type="time" name="timeOfDeparture" value="10:00" readonly></p>
                <p><label class="label">Station Of Departure</label>
                    <input type="text" name="Route Title" value="StationOfDeparture" readonly></p>
                <p><label class="label">Date of Arrival</label>
                    <input type="date" name="dateOfDeparture" value="2016-07-07" readonly></p>
                <p><label class="label">Time of Arrival</label>
                    <input type="time" name="timeOfDeparture" value="10:00" readonly></p>
                <p><label class="label">Station Of Arrival</label>
                    <input type="text" name="Route Title" value="StationOfArrival" readonly></p>
            </div>-->
            <div class="right_col">
                <h3>Passenger information</h3>
                <p><label for="fname" class="label">First Name</label>
                    <input type="text" name="fname" id="fname"></p>
                <p><label for="lname" class="label">Last Name</label>
                    <input type="text" name="lname" id="lname"></p>
                <p><label for="dateOfBirth" class="label">Date of Birth</label>
                    <input type="date" name="dateOfBirth" id="dateOfBirth"></p>
                <p>
                    <span id="errorMessage"></span>
                </p>
                <input id = "buyTicketButton" type="button" value="Buy Ticket">
            </div>
        </div>
        <span hidden="true" id="trainId">${trainId}</span>
        <span hidden="true" id="stationOfDepartureId">${stationOfDepartureId}</span>
        <span hidden="true" id="stationOfArrivalId">${stationOfArrivalId}</span>

    </form>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/ticketPurchaseForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

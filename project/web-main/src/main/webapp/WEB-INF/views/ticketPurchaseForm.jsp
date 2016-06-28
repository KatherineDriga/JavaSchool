
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Ticket Purchase Form</h2>
    <form role="form" class="buy_ticket">
        <div class="form-group">
        <!-- <div class="wrap"> -->
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
         <!--   <div class="right_col"> -->
                <h3>Passenger information</h3>
                <p><label for="fname">First Name</label>
                    <input class="form-control" type="text" name="fname" id="fname" required></p>
                <p><label for="lname">Last Name</label>
                    <input class="form-control" type="text" name="lname" id="lname" required></p>
                <p><label for="dateOfBirth">Date of Birth</label>
                    <input class="form-control" type="date" name="dateOfBirth" id="dateOfBirth" required></p>

                <input class="btn btn-success" id = "buyTicketButton" type="button" value="Buy Ticket">
            <!-- </div> -->
        </div>
        <!-- </div> -->
        <span hidden="true" id="trainId">${trainId}</span>
        <span hidden="true" id="stationOfDepartureId">${stationOfDepartureId}</span>
        <span hidden="true" id="stationOfArrivalId">${stationOfArrivalId}</span>

    </form>
    <div>
        <span id="errorMessage"></span><br />
        <span id="errorMessage1"></span><br />
        <span id="errorMessage2"></span>
    </div>

</section>
<script src="static/scripts/ticketPurchaseForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

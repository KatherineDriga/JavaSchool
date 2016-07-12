
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Ticket Purchase Form</h2>

    <form role="form" class="buy_ticket">
        <div class="form-group">
        <div class="wrap">
            <div class="left_col">
                <h3>Route information</h3>
                <p><label>Route Title</label>
                    <input type="text" class="form-control" value=${trainInfo.routeTitle} readonly></p>
                <p><label>Date of Departure</label>
                    <input type="date" class="form-control" value=${trainInfo.dateOfDeparture} readonly></p>
                <p><label>Time of Departure</label>
                    <input type="time" class="form-control" value=${trainInfo.timeOfDeparture} readonly></p>
                <p><label>Station Of Departure</label>
                    <input type="text" class="form-control" value=${trainInfo.stationOfDeparture} readonly></p>
                <p><label>Date of Arrival</label>
                    <input type="date" class="form-control" value=${trainInfo.dateOfArrival} readonly></p>
                <p><label>Time of Arrival</label>
                    <input type="time" class="form-control" value=${trainInfo.timeOfArrival} readonly></p>
                <p><label>Station Of Arrival</label>
                    <input type="text" class="form-control" value=${trainInfo.stationOfArrival} readonly></p>
                 </div>
            <div class="right_col">
                <h3>Passenger information</h3>
                <p><label for="fname">First Name</label>
                    <input class="form-control" type="text" name="fname" id="fname" required value="${account.firstName}"></p>
                <p><label for="lname">Last Name</label>
                    <input class="form-control" type="text" name="lname" id="lname" required value="${account.lastName}"></p>
                <p><label for="dateOfBirth">Date of Birth</label>
                    <input class="form-control" type="date" name="dateOfBirth" id="dateOfBirth" required value="${account.dateOfBirth}"></p>

                <input class="btn btn-success" id = "buyTicketButton" type="button" value="Buy Ticket">
            </div>
        </div>
        </div>
        <span hidden="true" id="trainId">${trainId}</span>
        <span hidden="true" id="stationOfDepartureId">${stationOfDepartureId}</span>
        <span hidden="true" id="stationOfArrivalId">${stationOfArrivalId}</span>

    </form>
    <div>
        <span id="errorMessage"></span><br />

    </div>

</section>
<script src="static/scripts/ticketPurchaseForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

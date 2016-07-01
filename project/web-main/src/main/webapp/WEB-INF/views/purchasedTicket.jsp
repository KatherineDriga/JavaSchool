
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h3>You've purchased ticket #${ticketInfo.id}!</h3>
    <br>
    <table class="table">
        <th></th>
        <th></th>
        <tr>
            <td><h4>Station of Departure:</h4></td>
            <td><h4>${ticketInfo.stationOfDeparture}</h4></td>
        </tr>
        <tr>
            <td><h4>Date and Time of Departure:</h4></td>
            <td><h4>${ticketInfo.datetimeOfDeparture}</h4></td>
        </tr>
        <tr>
            <td><h4>Station of Arrival:</h4></td>
            <td><h4>${ticketInfo.stationOfArrival}</h4></td>
        </tr>
        <tr>
            <td><h4>Date and Time of Arrival:</h4></td>
            <td><h4>${ticketInfo.datetimeOfArrival}</h4></td>
        </tr>
        <tr>
            <td><h4>Ticket Price:</h4></td>
            <td><h4>${ticketInfo.price}</h4></td>
        </tr>
    </table>


    <!--<form class="ticket_info">

        <div class="wrap">
            <div class="left_col">
                <h3>Route information</h3>
                <p><label class="label">Route Title</label></p>
                <p><label class="label">Date of Departure</label></p>
                <p><label class="label">Time of Departure</label></p>
                <p><label class="label">Station Of Departure</label></p>
                <p><label class="label">Date of Arrival</label></p>
                <p><label class="label">Time of Arrival</label></p>
                <p><label class="label">Station Of Arrival</label></p>
            </div>
            <div class="right_col">
                <h3>Passenger information</h3>
                <p><label class="label">First Name</label></p>
                <p><label class="label">Last Name</label></p>
                <p><label class="label">Date of Birth</label></p>
                <p><label class="label">Additional comments </label></p>
            </div>
        </div>-->


    </form>

</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

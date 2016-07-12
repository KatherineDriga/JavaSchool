<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Tickets</h2>
    <div>
        <form role="form" class="form-inline">
            <div class="form-group">
                <label class="control-label">Since </label><input class="form-control" id="sinceDate" type="date" value="${sinceDate}">
            </div>
            <div class="form-group">
                <label class="control-label">Until </label><input class="form-control" id="untilDate" type="date" value="${untilDate}">
            </div>
            <input class="btn btn-success" id = "searchTicketsReport" type="button" value="Search">
        </form>
    </div>
    <br>
    <c:choose>
        <c:when test="${empty tickets}">
            <h4>No tickets found! Try to change dates.</h4>
        </c:when>
        <c:otherwise>
            <div class="scroll-table">
                <table class="table table-striped">
                    <tr>
                        <th>#</th>
                        <th>Train</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>Passenger</th>
                        <th>Date and Time of Purchase</th>
                        <th>Price</th>
                        <th>Login</th>
                    </tr>
                    <c:forEach var="ticket" items="${tickets}">
                        <tr>
                            <td>${ticket.id}</td>
                            <td>${ticket.trainId}</td>
                            <td>${ticket.stationOfDeparture} <br/>${ticket.datetimeOfDeparture}</td>
                            <td>${ticket.stationOfArrival} <br />${ticket.datetimeOfArrival}</td>
                            <td>${ticket.firstName} <br />${ticket.lastName} <br />${ticket.dateOfBirth}</td>
                            <td>${ticket.datetimeOfPurchase}</td>
                            <td>${ticket.price}</td>
                            <td>${ticket.login}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </c:otherwise>
    </c:choose>
</section>
<script src="static/scripts/ticketsPurchasedReport.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

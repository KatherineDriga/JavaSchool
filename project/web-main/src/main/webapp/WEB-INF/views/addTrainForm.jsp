
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add train</h2>
    <form role="form" class="addTrain" method="post">
        <div class="form-group">

            <p><label>Route</label>
                <select class="form-control" id="route">
                    <option value="null">Select</option>
                    <c:forEach var="route" items="${routes}">
                        <option value="${route.id}">${route.title}</option>
                    </c:forEach>
                </select>
            </p>
            <p><label>Date of Departure</label>
                <input class="form-control" type="date" id = "dateOfDeparture" />
            </p>
            <p><label>Number of Seats</label>
                <input class="form-control" type="number" id = "numberOfSeats" />
            </p>
            <p><label>Price Coefficient</label>
                <input class="form-control" type="number" step="0.01" id = "priceCoefficient" />
            </p>


            <input class="btn btn-success" id = "addTrainButton" type="button" value="Add Train">
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
    </form>


</section>
<script src="static/scripts/addTrainForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


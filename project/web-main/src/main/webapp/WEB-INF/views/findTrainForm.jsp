
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Find trains</h2>
    <div style="width: 250px">
    <form role="form" class="findTrains">
        <div class="form-group">
        <p><label>Route</label>
            <select class="form-control" id="route">
                <option value="null">Select</option>
                <c:forEach var="route" items="${routes}">
                    <option value="${route.id}">${route.title}</option>
                </c:forEach>
            </select>
        </p>


        <input class="btn btn-success" id = "chooseRouteButton" type="button" value="Show trains">
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
    </form>
    </div>


</section>
<script src="static/scripts/findTrainForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


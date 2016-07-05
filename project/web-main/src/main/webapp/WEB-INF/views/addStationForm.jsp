<%@page session="true"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add station</h2>


    <form role="form" class="addStation" method="post">
        <div class="form-group">
                <p><label>Station</label>
                    <input class="form-control" type="text" id="stationTitle" required></p>
            <p><label>Timezone</label>
                <input class="form-control" type="number" id="timezone" required></p>

                <input class="btn btn-success" id = "addStationButton" type="button" value="Add Station">
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>



</section>
<script src="static/scripts/addStationForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


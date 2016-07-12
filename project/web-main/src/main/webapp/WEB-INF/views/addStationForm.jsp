<%@page session="true"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add station</h2>

    <div style="width: 250px">
    <form role="form" class="addStation" method="post">
        <div class="form-group">
                <p><label>Station</label>
                    <input class="form-control" type="text" id="stationTitle" required></p>
            <p><label>Timezone</label>
                <input class="form-control" type="number" min="-12" max="14" id="timezone" required></p>

                <input class="btn btn-success" id = "addStationButton" type="button" value="Add Station">
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>

    </form>
    </div>



</section>
<script src="static/scripts/addStationForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


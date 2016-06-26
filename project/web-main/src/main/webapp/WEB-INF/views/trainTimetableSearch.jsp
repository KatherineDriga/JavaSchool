
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Train Search</h2>
    <div class="columns">
        <div class="left_column">
    <form role="form">
        <div class="form-group">
        <p><label class="control-label">Station Of Departure </label>
            <select class="form-control" id="stationOfDeparture">
            <option value="null">Select</option>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.id}">${station.title}</option>
                </c:forEach>
        </select></p>
            <br>
        <p><label class="control-label">Station Of Arrival </label>
            <select class="form-control" id="stationOfArrival">
            <option value="null">Select</option>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.id}">${station.title}</option>
                </c:forEach>
        </select></p>
            <br><br><br>
            <p><input class="btn btn-success" id = "searchTrains" type="button" value="Search"></p>
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
        </form>
            </div>

        <div class="right_column">
            <form role="form">
                <div class="form-group">
        <p><label class="control-label">Since </label><input class="form-control" id="sinceDate" type="date" value="${sinceDate}"><input class="form-control" id="sinceTime" type="time" value="00:00" /></p>
        <p><label class="control-label">Until </label><input class="form-control" id="untilDate" type="date" value="${untilDate}"><input class="form-control" id="untilTime" type="time" value="23:59"/></p>
            </div>
    </form>
            </div>
        </div>

</section>
<span>example = ${example}</span>
<script src="/scripts/trainTimetableSearch.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

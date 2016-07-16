<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Station Timetable</h2>
    <div style="width: 250px">
    <form role="form" class="stationTimetableSearchForm">
        <div class="form-group">
        <p><label>Station </label>
            <select id="station" name="station" class="form-control">
                <option value="null">Select</option>
                <c:forEach var="station" items="${stations}">
                <option value="${station.id}">${station.title}</option>
                </c:forEach>
            </select></p>
        <p><label>Since </label><input class="form-control" id="sinceDate" type="date" value="${sinceDate}"><input class="form-control" id="sinceTime" type="time" value="00:00" /></p>
        <p><label>Until </label><input class="form-control" id="untilDate" type="date" value="${untilDate}"><input class="form-control" id="untilTime" type="time" value="23:59"/></p>
        <p><input class="btn btn-success" id = "search" type="button" value="Show timetable"></p>
            <div>
                <span id="errorMessage"></span>
            </div>
        </div>
    </form>
    </div>
</section>
<script src="static/scripts/stationTimetableSearch.js"></script>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

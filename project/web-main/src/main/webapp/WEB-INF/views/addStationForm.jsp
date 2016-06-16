
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Add station</h2>
    <form class="addStation" method="post">

                <p><label>Station</label>
                    <input type="text" id="stationTitle"></p>

                <input id = "addStationButton" type="button" value="Add Station">

    </form>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<script src="/scripts/addStationForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


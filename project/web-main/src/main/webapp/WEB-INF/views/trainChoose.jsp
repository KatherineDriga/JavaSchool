<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2>Choose train:</h2>
    <table class="table">
        <tr>
            <th></th>
            <th>Train</th>
            <th>Route Title</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Number Of Seats Available</th>
        </tr>

        <tr>
            <td><input type="radio" /></td>
            <td>TrainId</td>
            <td>Route</td>
            <td>Date<br />Time<br />Station</td>
            <td>Date<br />Time<br />Station</td>
            <td>Seats</td>
        </tr>
    </table>

    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Station Timetable</h2>
    <form class="station_timetable_search_form">
        <p><label>Station </label><select>
            <option>Select</option>
        </select></p>
        <p><label>Since </label><input type="date"><input type="time" value="00:00" /></p>
        <p><label>Until </label><input type="date"><input type="time" value="23:59"/></p>
        <p><input type="submit" value="Search"></p>
    </form>
    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

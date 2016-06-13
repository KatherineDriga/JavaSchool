
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Train Search</h2>
    <form class="train_timetable_search_form">
        <p><label>Station Of Departure </label><select>
            <option>Select</option>
        </select></p>
        <p><label>Station Of Arrival </label><select>
            <option>Select</option>
        </select></p>
        <p><label>Since </label><input type="date"><input type="time" value="00:00" /></p>
        <p><label>Until </label><input type="date"><input type="time" value="23:59"/></p>
        <p><input type="submit" value="Search"></p>
    </form>
    <%@include file="/WEB-INF/jspf/image.jspf" %>
</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

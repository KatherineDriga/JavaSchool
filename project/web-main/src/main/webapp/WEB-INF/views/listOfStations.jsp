<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

    <section>
        <ul>
        <c:forEach var="station" items="${stationList}">
            <li>${station.title}</li>
        </c:forEach>
            </ul>
    </section>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>

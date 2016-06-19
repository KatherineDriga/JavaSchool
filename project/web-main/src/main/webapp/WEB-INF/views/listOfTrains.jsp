
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>
<section>
<table>
<tr>
    <th>Train_ID</th>
    <th>Route</th>
    <th>Date of Departure</th>
</tr>

    <c:forEach var="train" items="${trainList}">
        <tr>
            <td>
       ${train.id}
            </td>
            <td>
                    ${train.route.title}
            </td>
            <td>
                    ${train.dateOfDeparture}
            </td>
            </tr>
    </c:forEach>

    </table>

</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

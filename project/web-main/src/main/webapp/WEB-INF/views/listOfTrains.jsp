<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 06.06.2016
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
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
</body>
</html>

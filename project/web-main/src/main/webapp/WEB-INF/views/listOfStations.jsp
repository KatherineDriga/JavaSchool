<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 06.06.2016
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Stations</title>
    </head>
    <body>
        <ul>
        <c:forEach var="station" items="${stationList}">
            <li>${station.title}</li>
        </c:forEach>
            </ul>
    </body>
</html>

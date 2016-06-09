<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 09.06.2016
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Model List</title>
</head>
<body>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Serial number</th>
    </tr>
    <c:forEach var="model" items="${models}">
        <tr>
            <td>${model.id}</td>
            <td>${model.title}</td>
            <td>${model.number}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>UberBahn</title>
    <link href="static/styles/bootstrap.css" rel="stylesheet" media="screen" type="text/css">
    <link href="static/styles/style.css" rel="stylesheet" type="text/css">
    <script src="static/scripts/jquery-3.0.0.min.js"></script>

</head>
<body>
<header>
    <p><a href="/"><img id = "logotype" src="static/images/train-large.png" alt="Image Not Found"></a></p>
    <h1>UberBahn</h1>

    <div class="logout">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h5>
                Welcome: ${pageContext.request.userPrincipal.name} |
                <a href="<c:url value="/j_spring_security_logout" />" class="link">Logout  </a>
            </h5>

        </c:if>
    </div>
    <br />
    <br />
    <div class="menu">
        <ul class="menuclass">
            <li><a href="/">Home</a></li>
            <li><a href="/">About us</a></li>
            <li><a href="/">Passenger information</a></li>
            <li><a href="/">Timetable</a></li>
            <li><a href="/">Career</a></li>
            <li><a href="/">Contacts</a></li>
        </ul>
    </div>
</header>

<nav>
    <h4 class="linkTitle">Clients Information</h4>
    <ul>
        <li class="link"><a href="/" class="link"><h5>Search for Train & Buy Ticket</h5></a></li>
        <li class="link"><a href="/stationTimetableSearch" class="link"><h5>Station Timetable</h5></a></li>
    </ul>
    <br />
    <br />
</nav>
<section>

    <h2 align="right">LOG IN or &nbsp;<a href="/signUpForm" class="darkLink" id="registration">SIGN UP</a> </h2>
    <br />
    <br />
    <div class="form-group">
        <form name="login" action="<c:url value='/j_spring_security_check' />"
              method='post' role="form">
            <p><div class="darkFormElement"><label>Login</label><input name='j_username' id="login" type="text" required="required"></div></p>
            <p><div class="darkFormElement"><label>Password</label><input name='j_password' id="password" type="password" required="required"></div></p>
            <p><div class="darkFormElement" id="login_button"><input name="submit" type="submit" value="Log in"></div></p>
            <br />
            <br />
            <br />
            <p><div><span id="errorMessage">${error}</span></div></p>
        </form>

    </div>

</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>

    <h2 align="right">LOG IN or &nbsp;<a href="/" class="darkLink" id="registration">SIGN UP</a> </h2>
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

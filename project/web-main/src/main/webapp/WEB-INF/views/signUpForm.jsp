
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h2>Registration</h2>
    <div class="columns">
        <div class="left_column">
            <form role="form">
                <div class="form-group">
                    <p>
                        <label class="control-label">Login</label>
                        <input class="form-control" id="login" type="text">
                    </p>
                    <p>
                        <label class="control-label">Email</label>
                        <input class="form-control" id="email" type="email">
                    </p>
                    <p>
                        <label class="control-label">Password</label>
                        <input class="form-control" id="password" type="password">
                    </p>
                    <p>
                        <label class="control-label">Confirm Password</label>
                        <input class="form-control" id="confPassword" type="password">
                    </p>
                    <p><input class="btn btn-success" id = "registerUser" type="button" value="Register"></p>
                    <div>
                        <span id="errorMessage"></span>
                    </div>
                </div>
            </form>
        </div>

        <div class="right_column">
            <form role="form">
                <div class="form-group">
                    <p>
                        <label class="control-label">First Name</label>
                        <input class="form-control" id="firstName" type="text">
                    </p>
                    <p>
                        <label class="control-label">Last Name</label>
                        <input class="form-control" id="lastName" type="text">
                    </p>
                    <p>
                        <label class="control-label">Date of Birth</label>
                        <input class="form-control" id="dateOfBirth" type="date">
                    </p>
                </div>
            </form>
        </div>
    </div>

</section>
<script src="static/scripts/signUpForm.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>

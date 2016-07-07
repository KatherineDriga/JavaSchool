
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/nav.jspf" %>

<section>
    <h3>You've successfully registered on on our website!</h3>
    <h4>You account: #${account.id}.</h4>
    <h4>Login: ${account.login}.</h4>
    <h4>Email: ${account.email}.</h4>
    <h4>First Name: ${account.firstName}.</h4>
    <h4>Last Name: ${account.lastName}.</h4>
    <h4>Date of birth: ${account.dateOfBirth}.</h4>
    <h3><a href="/loginPage" class="darkLink">Click here  </a>&nbsp; to continue. </h3>

</section>
<%@include file="/WEB-INF/jspf/footer.jspf" %>


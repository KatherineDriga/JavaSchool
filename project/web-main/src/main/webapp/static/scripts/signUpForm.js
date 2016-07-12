$(function () {

    $("#registerUser").click(function () {


        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var login = $("#login").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var dateOfBirth = $("#dateOfBirth").val();
        var employee = false;
        
        if ($("#employeeCheck").prop('checked')){
            employee = true;
        } 

        if ($("#login").val().length === 0) {
            errorMessageSpan.text("Enter login");
        } else if ($("#email").val().length === 0) {
            errorMessageSpan.text("Enter email");
        } else if ($("#password").val().length === 0) {
            errorMessageSpan.text("Enter password");
        } else if ($("#password").val() !== $("#confPassword").val()){
            errorMessageSpan.text("Confirmation does not match password");
        } else if ($("#firstName").val().length === 0) {
            errorMessageSpan.text("Enter first name");
        }  else if ($("#lastName").val().length === 0) {
            errorMessageSpan.text("Enter last name");
        }  else if ($("#dateOfBirth").val().length === 0) {
            errorMessageSpan.text("Enter date of birth");
        }  
        else  {
            $.ajax({
                type: "POST",
                url: "/signUpAccount",
                data: {
                    login: login,
                    email: email,
                    password: password,
                    firstName: firstName,
                    lastName: lastName,
                    dateOfBirth: dateOfBirth,
                    employee: employee
                },
                success: function (data) {
                    window.location.href = "/addedAccount?"
                        + "accountId=" + data.id;
                },
                error: function (error) {
                    errorMessageSpan.text(error.responseText);
                }
            });
        }
    });
});

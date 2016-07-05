$(function () {

    $("#buyTicketButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");
        var errorMessageSpan1 = $("#errorMessage1");
        errorMessageSpan1.text("");
        var errorMessageSpan2 = $("#errorMessage2");
        errorMessageSpan2.text("");

        var stationA = $("#stationOfDepartureId").text();
        var stationB = $("#stationOfArrivalId").text();
        var trainId = $("#trainId").text();
        var fname = $("#fname").val();
        var lname = $("#lname").val();
        var dateOfBirth = $("#dateOfBirth").val();
        var accountLogin = $("#accountLogin").text();

        if ($("#fname").val() == 0) {
            errorMessageSpan.text("Enter first name");
        }
        else if ($("#lname").val() == 0) {
            errorMessageSpan.text("Enter last name");
        }
        else if ($("#dateOfBirth").val() == 0) {
            errorMessageSpan.text("Enter date of birth");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/ticketPurchase",
                data: {
                    stationOfDepartureId: stationA,
                    stationOfArrivalId: stationB,
                    trainId: trainId,
                    firstName: fname,
                    lastName: lname,
                    dateOfBirth: dateOfBirth,
                    accountLogin: accountLogin
                },
                success: function (data) {
                    window.location.href = "/purchasedTicket?"
                        + "ticketId=" + data.id;
                },
                error: function (error) {
                    errorMessageSpan.text("Passenger is already registered");
                    errorMessageSpan1.text("Or No seats available");
                    errorMessageSpan2.text("Or Less than 10 minutes before departure");

                    //errorMessageSpan.text("Purchase Error");

                }
            });
        }
        
    });
});


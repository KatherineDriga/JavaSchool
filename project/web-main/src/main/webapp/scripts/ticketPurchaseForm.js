$(function () {

    $("#buyTicketButton").click(function () {

        var errorMessageSpan = $("#errorMessage");

        errorMessageSpan.text("");

        var stationA = $("#stationOfDepartureId").text();
        var stationB = $("#stationOfArrivalId").text();
        var trainId = $("#trainId").text();
        var fname = $("#fname").val();
        var lname = $("#lname").val();
        var dateOfBirth = $("#dateOfBirth").val();

        $.ajax({
            type: "POST",
            url: "/ticketPurchase",
            data: {
                stationOfDepartureId: stationA,
                stationOfArrivalId: stationB,
                trainId: trainId,
                firstName: fname,
                lastName: lname,
                dateOfBirth: dateOfBirth
            },
            success: function (data) {
                window.location.href = "/purchasedTicket?"
                    + "ticketId=" + data.id;
            },
            error: function (error) {
                errorMessageSpan.text(error);
            }
        });
        
    });
});


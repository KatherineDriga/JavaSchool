$(function () {

    $("#addTrainButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var routeId = $("#route").val();
        var dateOfDeparture = $("#dateOfDeparture").val();
        var numberOfSeats = $("#numberOfSeats").val();

        if ($("#route").val() == "null") {
            errorMessageSpan.text("Choose route");
        }
        else if ($("#dateOfDeparture").val().length == 0){
            errorMessageSpan.text("Enter date of departure");
        }
        else if ($("#numberOfSeats").val().length == 0 || $("#numberOfSeats").val() < 1){
            errorMessageSpan.text("Enter number of seats");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/addTrain",
                data: {
                    routeId: routeId,
                    dateOfDeparture: dateOfDeparture,
                    numberOfSeats: numberOfSeats
                },
                success: function (data) {
                    window.location.href = "/addedTrain?"
                        + "trainId=" + data.id + "&"
                        + "message=" + data.message;
                },
                error: function (error) {
                    errorMessageSpan.text("Train already exists");
                }
            });
        }
    });
});


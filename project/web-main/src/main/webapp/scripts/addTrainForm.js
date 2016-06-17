$(function () {

    $("#addTrainButton").click(function () {


        var routeId = $("#route").val();
        var dateOfDeparture = $("#dateOfDeparture").val();
        var numberOfSeats = $("#numberOfSeats").val();

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
            error: function () {
                alert("error");
            }
        });

    });
});


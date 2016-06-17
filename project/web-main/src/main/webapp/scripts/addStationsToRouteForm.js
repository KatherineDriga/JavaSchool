$(function () {

    $("#addRouteButton").click(function () {


        var stations = $("#stationOfDepartureId").text();
        var minutesSinceDepartures = $("#stationOfArrivalId").text();


        $.ajax({
            type: "POST",
            url: "/addRoute",
            data: {
                stations: [],
                minutesSinceDepartures: []
            },
            success: function (data) {
                window.location.href = "/addedRoute?"
                    + "routeId=" + data.id + "&"
                    + "routeTitle=" + data.title;
            },
            error: function () {
                alert("error");
            }
        });

    });
});


$(function () {

    $("#addRouteButton").click(function () {

        var routeTitle = $("#routeTitle").text();
        var timeOfDeparture = $("#timeOfDeparture").text();

        var stationIds = [];
        $(".stationId").each(function () {
            stationIds.push($(this).val());
        });
        var minutesSinceDepartures = [];
        $(".minutesSinceDeparture").each(function () {
            minutesSinceDepartures.push($(this).val());
        });


        $.ajax({
            type: "POST",
            url: "/addRoute",
            data: {
                title: routeTitle,
                timeOfDeparture: timeOfDeparture,
                stationIds: stationIds.join(";"),
                minutesSinceDepartures: minutesSinceDepartures.join(";")
            },
            success: function (data) {
                window.location.href = "/routeInfo?"
                    + "routeId=" + data.id;
            },
            error: function () {
                alert("error");
            }
        });

    });
});


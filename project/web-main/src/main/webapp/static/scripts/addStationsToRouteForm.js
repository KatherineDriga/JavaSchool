$(function () {

    $("#addRouteButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");
        var routeTitle = $("#routeTitle").text();
        var timeOfDeparture = $("#timeOfDeparture").text();
        var pricePerMinute = $("#pricePerMinute").text();


        $(".stationId").each(function () {
            if($(this).val() == "null") {
                errorMessageSpan.text("Select stations");
            }
        });
        $(".minutesSinceDeparture").each(function () {
           if($(this).val() == ""){
               errorMessageSpan.text("Enter minutes since departure");
           }
        });

        var stationIds = [];
        $(".stationId").each(function () {
            stationIds.push($(this).val());
        });
        var minutesSinceDepartures = [];
        $(".minutesSinceDeparture").each(function () {
            minutesSinceDepartures.push($(this).val());
        });

        if (errorMessageSpan.text() != "Enter minutes since departure" &&
            errorMessageSpan.text() != "Select stations") {
            $.ajax({
                type: "POST",
                url: "/addRoute",
                data: {
                    title: routeTitle,
                    timeOfDeparture: timeOfDeparture,
                    stationIds: stationIds.join(";"),
                    minutesSinceDepartures: minutesSinceDepartures.join(";"),
                    pricePerMinute: pricePerMinute
                },
                success: function (data) {
                    window.location.href = "/routeInfo?"
                        + "routeId=" + data.id;
                },
                error: function (error) {
                    errorMessageSpan.text("Route already exists or stations reiterave");
                }
            });
        }
    });
});


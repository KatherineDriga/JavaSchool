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


        function checkArray(A)
        {
            var i=0, j=0, L=A.length;
            do { j = i+1;
                while (A[i]!=A[j] && j<L) j++;
                i++;
            } while (i<L-1 && j==L);
            if (j<L) return false;
            return true;
        }

        if (checkArray(stationIds) == false) {
            errorMessageSpan.text("Stations reiterave");
        }

        if (checkArray(minutesSinceDepartures) == false) {
            errorMessageSpan.text("Minutes reiterave");
        }

        if (errorMessageSpan.text() != "Enter minutes since departure" &&
            errorMessageSpan.text() != "Select stations" &&
            errorMessageSpan.text() != "Stations reiterave" &&
            errorMessageSpan.text() != "Minutes reiterave") {
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
                    errorMessageSpan.text(error.responseText);
                }
            });
        }
    });
});


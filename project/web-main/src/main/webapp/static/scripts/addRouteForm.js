$(function () {

    $("#addStationsButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var routeTitle = $("#routeTitle").val();
        var numberOfStations = $("#numberOfStations").val();
        var timeOfDeparture = $("#timeOfDeparture").val();


        if ($("#routeTitle").val().length == 0) {
            errorMessageSpan.text("Enter route title");
        }
        else if ($("#numberOfStations").val() < 2){
            errorMessageSpan.text("Enter number of stations");
        }
        else {
            window.location.href = "/addStationsToRouteForm?"
                + "routeTitle=" + routeTitle + "&"
                + "numberOfStations=" + numberOfStations + "&"
                + "timeOfDeparture=" + timeOfDeparture;
        }
    });
});

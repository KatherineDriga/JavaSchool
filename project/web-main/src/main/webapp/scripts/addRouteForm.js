$(function () {

    $("#addStationsButton").click(function () {


        var routeTitle = $("#routeTitle").val();
        var numberOfStations = $("#numberOfStations").val();
        var timeOfDeparture = $("#timeOfDeparture").val();
        

        window.location.href = "/addStationsToRouteForm?"
            + "routeTitle=" + routeTitle + "&"
            + "numberOfStations=" + numberOfStations + "&"
            + "timeOfDeparture=" + timeOfDeparture;
    });
});

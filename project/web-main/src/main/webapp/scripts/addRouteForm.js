$(function () {

    $("#addStationsButton").click(function () {


        var routeTitle = $("#routeTitle").val();
        var numberOfStations = $("#numberOfStations").val();
        

        window.location.href = "/addStationsToRouteForm?"
            + "routeTitle=" + routeTitle + "&"
            + "numberOfStations=" + numberOfStations;
    });
});

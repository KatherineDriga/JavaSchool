$(function () {

    $("#chooseRouteButton").click(function () {


        var routeId = $("#route").val();
        
        window.location.href = "/tableOfTrains?"
            + "routeId=" + routeId;
    });
});

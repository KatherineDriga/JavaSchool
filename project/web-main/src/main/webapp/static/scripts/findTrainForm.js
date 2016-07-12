$(function () {

    $("#chooseRouteButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");
        var routeId = $("#route").val();

        if ($("#route").val() === "null") {
            errorMessageSpan.text("Choose route");
        }
        else {
            window.location.href = "/tableOfTrains?"
                + "routeId=" + routeId;
        }
    });

});

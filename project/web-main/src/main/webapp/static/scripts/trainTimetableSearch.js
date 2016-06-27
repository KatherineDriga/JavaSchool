$(function () {

    $("#searchTrains").click(function () {


        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var stationA = $("#stationOfDeparture").val();
        var stationB = $("#stationOfArrival").val();

        var sinceDate = $("#sinceDate").val();
        var sinceTime = $("#sinceTime").val();
        var untilDate = $("#untilDate").val();
        var untilTime = $("#untilTime").val();

        var since = sinceDate + 'T' + sinceTime;
        var until = untilDate + 'T' + untilTime;

        if ($("#stationOfDeparture").val() == "null") {
            errorMessageSpan.text("Station of departure is not selected");
        } else if ($("#stationOfArrival").val() == "null") {
            errorMessageSpan.text("Station of arrival is not selected");
        } else if ($("#stationOfDeparture").val() == $("#stationOfArrival").val()){
            errorMessageSpan.text("Stations of departure and arrival must be different");
        } else if ($("#sinceDate").val().length == 0) {
            errorMessageSpan.text("Enter since date");
        }  else if ($("#sinceTime").val().length == 0) {
            errorMessageSpan.text("Enter since time");
        }  else if ($("#untilDate").val().length == 0) {
            errorMessageSpan.text("Enter until date");
        }  else if ($("#untilTime").val().length == 0) {
            errorMessageSpan.text("Enter until time");
        } else {
        window.location.href = "/listOfTrains?"
            + "stationOfDeparture=" + stationA + "&"
            + "stationOfArrival=" + stationB + "&"
            + "since=" + since + "&"
            + "until=" + until;
    }
    });
});

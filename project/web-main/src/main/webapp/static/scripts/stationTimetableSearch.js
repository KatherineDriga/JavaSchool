$(function () {

    $("#search").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var stationId = $("#station").val();
        var sinceDate = $("#sinceDate").val();
        var sinceTime = $("#sinceTime").val();
        var untilDate = $("#untilDate").val();
        var untilTime = $("#untilTime").val();

        var since = sinceDate+'T'+sinceTime;
        var until = untilDate+'T'+untilTime;

        if ($("#station").val() === "null") {
            errorMessageSpan.text("Station is not selected");
        } else if ($("#sinceDate").val().length === 0) {
            errorMessageSpan.text("Enter since date");
        }  else if ($("#sinceTime").val().length === 0) {
            errorMessageSpan.text("Enter since time");
        }  else if ($("#untilDate").val().length === 0) {
            errorMessageSpan.text("Enter until date");
        }  else if ($("#untilTime").val().length === 0) {
            errorMessageSpan.text("Enter until time");
        }
        else {
        window.location.href = "/stationTimetable?"
            + "stationId=" + stationId + "&"
            + "since=" + since + "&"
            + "until=" + until;
        }
    });
});

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

        if ($("#station").val() == "null") {
            errorMessageSpan.text("Station is not selected");
        }
        else {
        window.location.href = "/stationTimetable?"
            + "stationId=" + stationId + "&"
            + "since=" + since + "&"
            + "until=" + until;
        }
    });
});

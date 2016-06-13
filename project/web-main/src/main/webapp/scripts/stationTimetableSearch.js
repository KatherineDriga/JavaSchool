$(function () {

    $("#search").click(function () {

        var stationId = $("#station").val();

        var sinceDate = $("#sinceDate");
        var sinceTime = $("#sinceTime");
        var untilDate = $("#untilDate");
        var untilTime = $("#untilTime");

        var since = "2012-01-01T00:00:00";
        var until = "2018-01-01T00:00:00";

        window.location.href = "/stationTimetable?"
            + "stationId=" + stationId + "&"
            + "since=" + since + "&"
            + "until=" + until;
    });
});

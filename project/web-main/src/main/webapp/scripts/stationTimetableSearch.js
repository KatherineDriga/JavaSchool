$(function () {

    $("#search").click(function () {

        var stationId = $("#station").val();

        var sinceDate = $("#sinceDate").val();
        var sinceTime = $("#sinceTime").val();
        var untilDate = $("#untilDate").val();
        var untilTime = $("#untilTime").val();

        var since = sinceDate+'T'+sinceTime;
        var until = untilDate+'T'+untilTime;

        window.location.href = "/stationTimetable?"
            + "stationId=" + stationId + "&"
            + "since=" + since + "&"
            + "until=" + until;
    });
});

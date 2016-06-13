$(function () {

    $("#searchTrains").click(function () {


        var stationA = $("#stationOfDeparture").val();
        var stationB = $("#stationOfArrival").val();

        var sinceDate = $("#sinceDate").val();
        var sinceTime = $("#sinceTime").val();
        var untilDate = $("#untilDate").val();
        var untilTime = $("#untilTime").val();

        var since = //sinceDate+'T'+sinceTime;
            "2012-01-01T00:00:00";
        var until = //untilDate+'T'+untilTime;
            "2018-01-01T00:00:00";

        window.location.href = "/trainTimetable?"
            + "stationOfDeparture=" + stationA + "&"
            + "stationOfArrival=" + stationB + "&"
            + "since=" + since + "&"
            + "until=" + until;
    });
});

$(function () {

    $("#searchTrains").click(function () {


        var stationA = $("#stationOfDeparture").val();
        var stationB = $("#stationOfArrival").val();

        var sinceDate = $("#sinceDate").val();
        var sinceTime = $("#sinceTime").val();
        var untilDate = $("#untilDate").val();
        var untilTime = $("#untilTime").val();

        var since = sinceDate+'T'+sinceTime;
        var until = untilDate+'T'+untilTime;

        window.location.href = "/trainChoose?"
            + "stationOfDeparture=" + stationA + "&"
            + "stationOfArrival=" + stationB + "&"
            + "since=" + since + "&"
            + "until=" + until;
    });
});

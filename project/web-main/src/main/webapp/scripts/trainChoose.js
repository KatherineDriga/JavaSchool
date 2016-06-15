$(function () {

    $("#chooseTrain").click(function () {


        var stationA = $("#stationOfDepartureId").text();
        var stationB = $("#stationOfArrivalId").text();

        var trainId = $("input[type='radio']:checked").val();


        window.location.href = "/ticketPurchaseForm?"
            + "stationOfDeparture=" + stationA + "&"
            + "stationOfArrival=" + stationB + "&"
            + "trainId=" + trainId;
    });
});


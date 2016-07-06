$(function () {

    $("#chooseTrain").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var stationA = $("#stationOfDepartureId").text();
        var stationB = $("#stationOfArrivalId").text();

        var trainId = $("input[type='radio']:checked").val();;

        if ($('input:radio:checked').length < 1) {
            errorMessageSpan.text("Choose train");
        }
        else {
            window.location.href = "/ticketPurchaseForm?"
                + "stationOfDeparture=" + stationA + "&"
                + "stationOfArrival=" + stationB + "&"
                + "trainId=" + trainId;
        }
    });
});


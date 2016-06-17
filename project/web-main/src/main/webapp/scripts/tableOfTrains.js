$(function () {

    $("#chooseTrainButton").click(function () {


        var trainId = $("input[type='radio']:checked").val();


        window.location.href = "/findPassengers?"
            + "trainId=" + trainId;
    });
});


$(function () {

    $("#chooseTrainButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var trainId = $("input[type='radio']:checked").val();

        if ($('input:radio:checked').length < 1) {
            errorMessageSpan.text("Choose train");
        }
        else {
            window.location.href = "/listOfPassengers?"
                + "trainId=" + trainId;
        }
    });
});


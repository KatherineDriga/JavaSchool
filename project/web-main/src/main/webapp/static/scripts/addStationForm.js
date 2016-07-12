$(function () {

    $("#addStationButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");
        var stationTitle = $("#stationTitle").val();
        var timezone = $("#timezone").val();

        if ($("#stationTitle").val().length === 0) {
            errorMessageSpan.text("Enter station title");
        }
        else if ($("#timezone").val().length === 0) {
            errorMessageSpan.text("Enter timezone");
        } else if ($("#timezone").val() < -12 || ($("#timezone").val() > 14)) {
            errorMessageSpan.text("Invalid timezone");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/addStation",
                data: {
                    stationTitle: stationTitle,
                    timezone: timezone
                },
                success: function (data) {
                    window.location.href = "/addedStation?"
                        + "stationId=" + data.id + "&"
                        + "title=" + data.title;
                },
                error: function (error) {
                    errorMessageSpan.text(error.responseText);
                }
            });
        }
    });
});


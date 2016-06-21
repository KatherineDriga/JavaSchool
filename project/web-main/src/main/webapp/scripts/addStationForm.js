$(function () {

    $("#addStationButton").click(function () {

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");
        var stationTitle = $("#stationTitle").val();

        if ($("#stationTitle").val().length == 0) {
            errorMessageSpan.text("Enter station title");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/addStation",
                data: {
                    stationTitle: stationTitle
                },
                success: function (data) {
                    window.location.href = "/addedStation?"
                        + "stationId=" + data.id + "&"
                        + "title=" + data.title;
                },
                error: function (error) {
                    /*window.location.href = "/purchasedTicketError?"
                     + "message=" + data.message;*/
                    errorMessageSpan.text("Station already exists");
                }
            });
        }
    });
});


$(function () {

    $("#addStationButton").click(function () {


        var stationTitle = $("#stationTitle").val();

        $.ajax({
            type: "POST",
            url: "/addStation",
            data: {
                stationTitle: stationTitle
            },
            success: function (data) {
                window.location.href = "/addedStation?"
                    + "stationId=" + data.id + "&"
                    + "title=" + data.title + "&"
                    + "message=" + data.message;
            },
            error: function () {
                /*window.location.href = "/purchasedTicketError?"
                 + "message=" + data.message;*/
                alert("error");
            }
        });

    });
});


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
                    + "title=" + data.title;
            },
            error: function (error) {
                /*window.location.href = "/purchasedTicketError?"
                 + "message=" + data.message;*/
                alert("Station already exists");
            }
        });

    });
});


$(function () {

    $("#searchTicketsReport").click(function () {


        var sinceDate = $("#sinceDate").val();
        var untilDate = $("#untilDate").val();

        window.location.href = "/ticketsPurchasedReport?"
            + "since=" + sinceDate + "&"
            + "until=" + untilDate;

    });
});

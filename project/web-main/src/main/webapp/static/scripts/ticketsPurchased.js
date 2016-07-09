$(function () {

    $("#searchTickets").click(function () {
        
        
        var sinceDate = $("#sinceDate").val();
        var untilDate = $("#untilDate").val();
        
            window.location.href = "/ticketsPurchased?"
                + "sinceDate=" + sinceDate + "&"
                + "untilDate=" + untilDate;
        
    });
});

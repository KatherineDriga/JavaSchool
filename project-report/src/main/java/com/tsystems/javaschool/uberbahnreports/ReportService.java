package com.tsystems.javaschool.uberbahnreports;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ReportService {

    File createPdf(Collection<TicketInfo> ticketInfos,
                   LocalDateTime datetimeSince,
                   LocalDateTime datetimeUntil);

    void uploadPDFToClient(File pdfFile) throws IOException;

    Collection<TicketInfo> getPurchasedTickets(String login, String password,
                                               LocalDateTime since, LocalDateTime until);

}

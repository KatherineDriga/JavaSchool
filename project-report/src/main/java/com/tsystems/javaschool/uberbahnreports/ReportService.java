package com.tsystems.javaschool.uberbahnreports;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public interface ReportService {

    File createPdf(Collection<TicketReport> ticketInfos,
                   LocalDate dateSince,
                   LocalDate dateUntil);

    void uploadPDFToClient(File pdfFile) throws IOException;

    Collection<TicketReport> getPurchasedTickets(String login, String password,
                                                 LocalDate since, LocalDate until);

}

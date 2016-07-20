package com.tsystems.javaschool.uberbahnreports;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ReportService {

    File createPdf(Collection<Tickets> ticketInfos,
                   LocalDate dateSince,
                   LocalDate dateUntil);

    void uploadPDFToClient(File pdfFile) throws IOException;

    Collection<Tickets> getPurchasedTickets(String login, String password,
                                            LocalDate since, LocalDate until);

}

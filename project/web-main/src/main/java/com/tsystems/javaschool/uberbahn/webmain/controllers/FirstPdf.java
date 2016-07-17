package com.tsystems.javaschool.uberbahn.webmain.controllers;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tsystems.javaschool.uberbahn.services.AccountService;
import com.tsystems.javaschool.uberbahn.services.TicketService;
import com.tsystems.javaschool.uberbahn.services.TrainService;
import com.tsystems.javaschool.uberbahn.transports.TicketInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Collection;

@Controller
public class FirstPdf {

    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, new BaseColor(70, 92, 113));
    private static Font tableHeadingFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, new BaseColor(255, 255, 255));
    private static BaseColor tableHeadingBackground = new BaseColor(70, 92, 113);

    private final TicketService ticketService;

    @Autowired
    public FirstPdf(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public void create() {
        LocalDateTime datetimeUntil = LocalDateTime.now();
        LocalDateTime datetimeSince = datetimeUntil.minusDays(60);

        String file = "c:/temp/Report_" + datetimeSince.toLocalDate() + "_" + datetimeUntil.toLocalDate() + ".pdf";

        Collection<TicketInfo> ticketInfos = ticketService.getTicketInfos(datetimeSince, datetimeUntil);
        try {
            Document document = new Document(PageSize.A4.rotate(), 0, 0, 15f, 0);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addTitle(document, datetimeSince, datetimeUntil);
            if (ticketInfos.size() == 0) {
                Paragraph paragraph = new Paragraph();
                paragraph.add(new Paragraph("No tickets purchased", titleFont));
                paragraph.setSpacingBefore(25);
                paragraph.setIndentationLeft(50);
                addEmptyLine(paragraph, 1);
                document.add(paragraph);
            } else {
                document.add(createTable(ticketInfos));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static PdfPTable createTable(Collection<TicketInfo> ticketInfos)
            throws BadElementException {
        PdfPTable table = new PdfPTable(9);

        PdfPCell c1 = new PdfPCell(new Phrase("Number", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Train", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Route Title", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Departure", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Arrival", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Passenger", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date and Time of Departure", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Login", tableHeadingFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(tableHeadingBackground);
        table.addCell(c1);

        table.setHeaderRows(1);

        ticketInfos.forEach(ticketInfo -> {
            table.addCell(String.valueOf(ticketInfo.getId()));
            table.addCell(String.valueOf(ticketInfo.getTrainId()));
            table.addCell(ticketInfo.getRouteTitle());
            table.addCell(ticketInfo.getStationOfDeparture() + " " + ticketInfo.getDateOfDeparture() + " " + ticketInfo.getTimeOfDeparture());
            table.addCell(ticketInfo.getStationOfArrival() + " " + ticketInfo.getDateOfArrival() + " " + ticketInfo.getTimeOfArrival());
            table.addCell(ticketInfo.getFirstName() + " " + ticketInfo.getLastName() + " " + ticketInfo.getDateOfBirth());
            table.addCell(ticketInfo.getDateOfPurchase() + " " + ticketInfo.getTimeOfPurchase());
            table.addCell(String.valueOf(ticketInfo.getPrice()));
            table.addCell(ticketInfo.getLogin());
        });

        return table;

    }


    private static void addTitle(Document document, LocalDateTime since, LocalDateTime until)
            throws DocumentException {
        Paragraph titleParagraph = new Paragraph();
        titleParagraph.add(new Paragraph(("Report " + since.toLocalDate() + " - " + until.toLocalDate()), titleFont));
        titleParagraph.setSpacingBefore(25);
        titleParagraph.setIndentationLeft(50);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(titleParagraph, 1);
        document.add(titleParagraph);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}

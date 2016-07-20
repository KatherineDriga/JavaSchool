package com.tsystems.javaschool.uberbahnreports;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@ManagedBean
@SessionScoped
public class ReportControllerImpl {

    @EJB
    private ReportService reportService;


    private String login;
    private String password;
    private Date since;
    private Date until;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }


    public void downloadPDF() throws IOException {
        Collection<TicketInfo> ticketInfos = reportService.getPurchasedTickets(getLogin(), getPassword(), LocalDateTime.now(), LocalDateTime.now());
        File pdfFile = reportService.createPdf(ticketInfos, LocalDateTime.now().minusDays(60), LocalDateTime.now());
        reportService.uploadPDFToClient(pdfFile);
        try {
            pdfFile.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}

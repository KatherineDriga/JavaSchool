package com.tsystems.javaschool.uberbahnreports;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public LocalDate convertDateToLocalDate (Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void downloadPDF() {
        LocalDate dateSince = convertDateToLocalDate(getSince());
        LocalDate dateUntil = convertDateToLocalDate(getUntil());
        Collection<Tickets> ticketInfos = reportService.getPurchasedTickets(getLogin(), getPassword(), dateSince, dateUntil);
        if (ticketInfos == null) {
            setErrMsg("Invalid login or password");
        } else {
            setErrMsg("");
            File pdfFile = reportService.createPdf(ticketInfos, dateSince, dateUntil);
            try {
                reportService.uploadPDFToClient(pdfFile);
                pdfFile.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


}

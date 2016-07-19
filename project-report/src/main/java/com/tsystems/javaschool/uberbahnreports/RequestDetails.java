package com.tsystems.javaschool.uberbahnreports;


import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@ManagedBean
@SessionScoped
public class RequestDetails {

    @EJB
    private PDF pdf;

    ClientConfig clientConfig = new DefaultClientConfig();
    private Client client = Client.create(clientConfig);


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

    public Collection<TicketInfo> getPurchasedTickets() {
        Collection<TicketInfo> ticketInfos = new ArrayList<>();
        try {
            WebResource target = client.resource("http://localhost:8080/ticketsPurchasedReport");
            ClientResponse clientResponse = target
                   .queryParam("login", getLogin())
                   .queryParam("password", getPassword())
                   .accept("application/json")
                   .header("content-type", "application/json")
                   .type("application/json")
                   .get(ClientResponse.class);
            ticketInfos = clientResponse.getEntity(new GenericType<Collection<TicketInfo>>() {});


        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return ticketInfos;
    }

    public void downloadPDF() throws IOException {
        File pdfFile = pdf.createPdf(getPurchasedTickets(), LocalDateTime.now().minusDays(60), LocalDateTime.now());
        pdf.uploadPDFToClient(pdfFile);
        try {
            pdfFile.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}

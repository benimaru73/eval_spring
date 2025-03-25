package com.itu.eval_spring.dto.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class InvoiceByClient {
    private int id;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("created_at")
    private LocalDateTime invoiceDate;
    @JsonProperty("due")
    private LocalDateTime due;
    @JsonProperty("amount_due")
    private double amount_due;
    @JsonProperty("reduction")
    private double reduction;
    @JsonProperty("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }
}

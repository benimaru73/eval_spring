package com.itu.eval_spring.dto.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PaymentByInvoice {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("invoice_date")
    private LocalDateTime invoiceDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("invoice_id")
    private int invoiceId;

    @JsonProperty("newAmountDue")
    private double newAmountDue;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getNewAmountDue() {
        return newAmountDue;
    }

    public void setNewAmountDue(double newAmountDue) {
        this.newAmountDue = newAmountDue;
    }
}

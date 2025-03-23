package com.itu.eval_spring.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentClientDTO {
    private int id;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("total_paid")
    private double totalPaid;

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

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }
}

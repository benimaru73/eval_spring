package com.itu.eval_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {
    private Long id;
    @JsonProperty("company_name")
    private String companyName;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}

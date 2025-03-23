package com.itu.eval_spring.dto;

import java.util.List;

public class ClientPaymentsDTO {
    private ClientDTO client;
    private List<PaymentDTO> payments;

    // Getters et Setters
    public ClientDTO getClient() { return client; }
    public void setClient(ClientDTO client) { this.client = client; }
    public List<PaymentDTO> getPayments() { return payments; }
    public void setPayments(List<PaymentDTO> payments) { this.payments = payments; }
}


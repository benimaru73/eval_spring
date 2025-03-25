package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.invoice.InvoiceByClient;
import com.itu.eval_spring.dto.payment.PaymentByInvoice;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://127.0.0.1:8000/api/invoices/";

    public List<InvoiceByClient> getAllInvoiceByClients() {
        String url = API_URL + "all";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceByClient>>() {}).getBody();
    }

    public List<InvoiceByClient> filterByStatus(List<InvoiceByClient> invoices, String status) {
        return invoices.stream()
                .filter(invoice -> invoice.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}

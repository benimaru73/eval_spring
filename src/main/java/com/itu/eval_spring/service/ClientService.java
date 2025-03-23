package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.ClientPaymentsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://127.0.0.1:8000/api/clients/payments/";

    public ClientPaymentsDTO getClientPayments(Long clientId) {
        String url = API_URL + clientId;
        return restTemplate.getForObject(url, ClientPaymentsDTO.class);
    }
}
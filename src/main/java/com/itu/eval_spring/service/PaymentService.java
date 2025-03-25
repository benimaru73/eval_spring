package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.payment.Payment;
import com.itu.eval_spring.dto.payment.PaymentByInvoice;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://127.0.0.1:8000/api/payments/";

    public List<PaymentByInvoice> getPaymentsGByInvoice() {
        String url = API_URL + "byinvoice";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PaymentByInvoice>>() {}).getBody();
    }

    public List<Payment> getPaymentByInvoice(int invoiceid) {
        String url = API_URL + invoiceid;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>() {}).getBody();
    }

    public Payment getPaymentById(int id) {
        String url = API_URL + "id/" + id;
        List<Payment> payments = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>() {}).getBody();
        return payments.get(0);
    }

    public void updatePayment(Payment payment) {
        String url = API_URL + payment.getId();
        restTemplate.put(url, payment);
    }

    public void updatePayment(String externalId, double montant) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "update";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("external_id", externalId); // Correction de la clé
        requestBody.put("amount", montant);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }

//    public void deletePayment(int id) {
//        String url = API_URL + id;
//        Map<String, String> body = new HashMap<>();
//        body.put("_method", "DELETE");
//
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//        restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//        restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
//    }

    public void delete(String externalId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "delete";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("external_id", externalId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }


}

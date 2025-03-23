package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.ClientPaymentsDTO;
import com.itu.eval_spring.dto.Payment.Payment;
import com.itu.eval_spring.dto.Payment.PaymentByInvoice;
import com.itu.eval_spring.dto.dashboard.PaymentClientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public void deletePayment(int id) {
        String url = API_URL + id;
        restTemplate.delete(url);
    }

}

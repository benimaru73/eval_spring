package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.dashboard.PaymentClientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://127.0.0.1:8000/api/";

    public int getClientCount() {
        String url = API_URL + "clients/count";

        String response = restTemplate.getForObject(url, String.class);
        return Integer.parseInt(response);
    }

    public List<PaymentClientDTO> getPaymentClientDTO() {
        String url = API_URL + "clients/payments";
        String response = restTemplate.getForObject(url, String.class);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PaymentClientDTO>>() {}).getBody();
    }


    public double[] getPaidAndUnpaid() {
        String url = API_URL + "payments";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        double sumPaid = response != null && response.get("sumPaid") != null ? Double.parseDouble(response.get("sumPaid").toString()) : 0.0;
        double sumDue = response != null && response.get("sumDue") != null ? Double.parseDouble(response.get("sumDue").toString()) : 0.0;

        return new double[]{sumPaid, sumDue};
    }
}

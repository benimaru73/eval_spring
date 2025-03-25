package com.itu.eval_spring.service;

import com.itu.eval_spring.dto.reduction.Reduction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReductionService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://127.0.0.1:8000/api/reductions/";

    public Reduction getReduction() {
        String url = API_URL + "get";
        return restTemplate.getForObject(url, Reduction.class);
    }

    public void createReduction(double taux) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("taux", taux);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }


    public void updateReduction(int id, double taux) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "update";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("taux", taux);
        requestBody.put("id", id);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }
}

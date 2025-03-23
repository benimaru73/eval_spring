package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.ClientDTO;
import com.itu.eval_spring.dto.ClientPaymentsDTO;
import com.itu.eval_spring.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService paymentService;

    public ClientController(ClientService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/{clientId}")
    public String getClientPayments(@PathVariable Long clientId, Model model) {
        ClientPaymentsDTO response = paymentService.getClientPayments(clientId);

        if (response != null) {
            model.addAttribute("client", response.getClient());
            model.addAttribute("payments", response.getPayments());
        } else {
            model.addAttribute("client", new ClientDTO());
            model.addAttribute("payments", List.of());
        }

        return "pages/payment/paymentsClients";
    }
}
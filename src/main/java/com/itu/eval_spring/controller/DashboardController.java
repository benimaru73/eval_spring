package com.itu.eval_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itu.eval_spring.dto.ClientDTO;
import com.itu.eval_spring.dto.ClientPaymentsDTO;
import com.itu.eval_spring.dto.dashboard.PaymentClientDTO;
import com.itu.eval_spring.service.ClientService;
import com.itu.eval_spring.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping()
    public String showCreateForm(Model model) {
        List<PaymentClientDTO> paymentClientDTO = dashboardService.getPaymentClientDTO();

        List<String> companyNames = paymentClientDTO.stream()
                .map(PaymentClientDTO::getCompanyName)
                .collect(Collectors.toList());

        List<Double> totalPaidValues = paymentClientDTO.stream()
                .map(PaymentClientDTO::getTotalPaid)
                .collect(Collectors.toList());

        double[] payment = dashboardService.getPaidAndUnpaid();

        model.addAttribute("companyNames", companyNames);
        model.addAttribute("totalPaidValues", totalPaidValues);
        model.addAttribute("paidAndUnpaid", payment);

        return "pages/dashboard/index";
    }




}

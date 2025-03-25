package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.dashboard.PaymentClientDTO;
import com.itu.eval_spring.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping()
    public String showCreateForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/index";
        }
        List<PaymentClientDTO> paymentClientDTO = dashboardService.getPaymentClientDTO();
        List<Integer> clientid = paymentClientDTO.stream()
                .map(PaymentClientDTO::getId)
                .collect(Collectors.toList());

        List<String> companyNames = paymentClientDTO.stream()
                .map(PaymentClientDTO::getCompanyName)
                .collect(Collectors.toList());

        List<Double> totalPaidValues = paymentClientDTO.stream()
                .map(PaymentClientDTO::getTotalPaid)
                .collect(Collectors.toList());

        double[] payment = dashboardService.getPaidAndUnpaid();

        // Récupération des factures par statut
        Map<String, Integer> invoiceStatusMap = dashboardService.getInvoicesByStatus();

        model.addAttribute("companyNames", companyNames);
        model.addAttribute("totalPaidValues", totalPaidValues);
        model.addAttribute("paidAndUnpaid", payment);
        model.addAttribute("invoiceStatusMap", invoiceStatusMap);
        model.addAttribute("clientid", clientid);

        return "pages/dashboard/index";
    }




}

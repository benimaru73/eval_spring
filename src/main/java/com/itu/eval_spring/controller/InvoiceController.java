package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.invoice.InvoiceByClient;
import com.itu.eval_spring.dto.payment.Payment;
import com.itu.eval_spring.dto.payment.PaymentByInvoice;
import com.itu.eval_spring.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping()
    public String getClientPaymentsByInvoice(Model model) {
        List<InvoiceByClient> response = invoiceService.getAllInvoiceByClients();

        if (response != null) {
            model.addAttribute("invoices", response);
        } else {
            model.addAttribute("invoices", List.of());
        }

        return "pages/invoice/form";
    }

}
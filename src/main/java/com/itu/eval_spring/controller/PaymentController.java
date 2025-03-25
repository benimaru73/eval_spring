package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.payment.Payment;
import com.itu.eval_spring.dto.payment.PaymentByInvoice;
import com.itu.eval_spring.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{invoiceId}")
    public String getClientPaymentsByInvoice(@PathVariable Integer invoiceId, Model model) {
        List<Payment> response = paymentService.getPaymentByInvoice(invoiceId);
        if (response != null && !response.isEmpty()) {
            model.addAttribute("payments", response);
        } else {
            model.addAttribute("payments", List.of());
        }

        return "pages/payment/all";
    }


    @GetMapping()
    public String getClientPaymentsByInvoice( Model model) {
        List<PaymentByInvoice> response = paymentService.getPaymentsGByInvoice();

        if (response != null) {
            model.addAttribute("paymentGByInvoice", response);
        } else {
            model.addAttribute("paymentGByInvoice", List.of());
        }

        return "pages/payment/paymentsGByInvoice";
    }

    @GetMapping("/edit/{id}")
    public String editPayment(@PathVariable("id") int id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("hello"+payment.getInvoiceId());
        model.addAttribute("payment", payment);
        return "pages/payment/form";
    }

    // Mise à jour du paiement
    @PostMapping("/edit")
    public String updatePayment(@RequestParam("id") int id, @RequestParam("amount") double amount){
        Payment payment = paymentService.getPaymentById(id);
        String external_id = payment.getExternalId();
        int invoiceId = payment.getInvoiceId();
        if (payment != null) {
            paymentService.updatePayment(external_id,amount);
        }
        return "redirect:/payments/"+invoiceId;
    }

    // Suppression du paiement
    @PostMapping("/delete")
    public String deletePayment(@RequestParam("id") int id){
        Payment payment = paymentService.getPaymentById(id);
        String external_id = payment.getExternalId();
        int invoiceId = payment.getInvoiceId();
        if (payment != null) {
            paymentService.delete(external_id);
        }
        return "redirect:/payments/"+invoiceId;
    }
}

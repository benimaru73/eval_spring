package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.Payment.Payment;
import com.itu.eval_spring.dto.Payment.PaymentByInvoice;
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
        for (Payment payment : response) {
            System.out.println("hello"+payment.getInvoiceId());
        }
        if (response != null && !response.isEmpty()) {
            model.addAttribute("payments", response);
        } else {
            model.addAttribute("payments", List.of()); // Liste vide si aucune donnée
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
        return "pages/payment/form"; // Page de mise à jour
    }

    // Mise à jour du paiement
    @PostMapping("/edit/{id}")
    public String updatePayment(@PathVariable("id") int id, @RequestParam("amount") double amount,
                                @RequestParam("description") String description) {
        Payment payment = paymentService.getPaymentById(id);
        int invoiceId = payment.getInvoiceId();
        if (payment != null) {
            payment.setAmount((double)amount);
            payment.setDescription(description);
            paymentService.updatePayment(payment);
        }
        return "redirect:/payments/"+invoiceId;
    }

    // Suppression du paiement
    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable("id") int id) {
        Payment payment = paymentService.getPaymentById(id);
        int invoiceId = payment.getInvoiceId();
        paymentService.deletePayment(id);
        return "redirect:/payments/"+invoiceId;
    }
}

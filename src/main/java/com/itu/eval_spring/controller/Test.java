package com.itu.eval_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class Test {

    @GetMapping()
    public String showCreateForm(Model model) {
        return "pages/paymentsClients";
    }
}

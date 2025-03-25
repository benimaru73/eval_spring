package com.itu.eval_spring.controller;

import com.itu.eval_spring.dto.reduction.Reduction;
import com.itu.eval_spring.service.ReductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reductions")
public class ReductionController {
    private ReductionService reductionService;

    public ReductionController(ReductionService reductionService) {
        this.reductionService = reductionService;
    }

    @GetMapping()
    public String getClientPayments(Model model) {
        Reduction response = reductionService.getReduction();

        if (response != null) {
            model.addAttribute("reduction", response);
            System.out.println(1);
        } else {
            System.out.println(2);
            model.addAttribute("reduction", null);
        }

        return "pages/reduction/form";
    }

    @PostMapping("/create")
    public String createReduction(@RequestParam("taux") double taux) {
        reductionService.createReduction(taux);
        return "redirect:/reductions";
    }

    @PostMapping("/edit/{id}")
    public String updateReduction(@PathVariable("id") Integer id, @RequestParam("taux") double taux) {
        reductionService.updateReduction(id.intValue(), taux);
        return "redirect:/reductions";
    }
}

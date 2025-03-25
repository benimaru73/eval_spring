package com.itu.eval_spring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.itu.eval_spring.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ResponseEntity<JsonNode> responseEntity = authService.login(username, password);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = responseEntity.getBody();
            session.setAttribute("user", jsonNode != null ? jsonNode.get("user") : null);
            return "redirect:/dashboard";
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "pages/login";
        } else {
            return "error";
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        return authService.logout(token.replace("Bearer ", ""));
    }
}

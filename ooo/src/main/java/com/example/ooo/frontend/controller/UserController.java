package com.example.ooo.frontend.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {
    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return "user";
    }
}

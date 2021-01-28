package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}

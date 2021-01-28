package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.User;
import com.geekbrains.geekspring.repositories.RoleRepository;
import com.geekbrains.geekspring.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String adminHome(Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName());
        String email = "unknown";
        if (user != null) {
            email = user.getEmail();
        }
        model.addAttribute("email", email);
        return "admin-panel";
    }

}

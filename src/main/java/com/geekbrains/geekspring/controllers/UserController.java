package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.User;
import com.geekbrains.geekspring.exceptions.NotFoundException;
import com.geekbrains.geekspring.repositories.RoleRepository;
import com.geekbrains.geekspring.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String indexUserPage(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user-list";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit user with id {}", id);

        model.addAttribute("roles", roleRepository.findAll());
        //обработать возможную ошибку ToDo
        model.addAttribute("user", userService.findById(id));
        return "user-form";
    }

    @PostMapping("/update")
    public String updateUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-form";
        }
        userService.save(user);
        return "redirect:/user-list";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        logger.info("Delete user with id {}", id);
        userService.deleteById(id);
        return "redirect:/user-list";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}

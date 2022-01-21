package com.example.bootspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class LoginController {
    @GetMapping({"/", "/login"})
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}

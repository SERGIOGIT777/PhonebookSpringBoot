package com.example.bootspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getAllLog() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/")
    public String go(){
        return "redirect:/home";
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

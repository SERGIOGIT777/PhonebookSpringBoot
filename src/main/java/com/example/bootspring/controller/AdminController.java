package com.example.bootspring.controller;

import com.example.bootspring.entity.Users;
import com.example.bootspring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ModelAndView viewAll() {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("roles", usersRepository.findAll());
        return mav;
    }


    @GetMapping("/addUsers")
    public ModelAndView addUserForm() {
        ModelAndView mav = new ModelAndView("admin");
        Users newUser = new Users();
        mav.addObject("role", newUser);
        mav.addObject("roles", usersRepository.findAll());
        return mav;
    }

    @PostMapping("/saveUsers")
    public String saveUsers(@Valid Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin";
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        model.addAttribute("roles", usersRepository.findAll());
        return "redirect:/adminPage/addUsers";
    }


}

package com.example.bootspring.controller;

import com.example.bootspring.entity.Application;
import com.example.bootspring.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping({"/listApplication", "/"})
    public ModelAndView getAllApplication() {
        ModelAndView mav = new ModelAndView("list-application");
        mav.addObject("applications",applicationRepository.findAll());
        return mav;
    }

    @GetMapping("/addApplicationForm")
    public ModelAndView addApplicationForm() {
        ModelAndView mav = new ModelAndView("addApplication");
        Application newApplication = new Application();
        mav.addObject("card", newApplication);
        return mav;
    }

    @PostMapping("/saveApplication")
    public String saveApplication(@ModelAttribute Application application) {
        applicationRepository.save(application);
        return "redirect:/application/listApplication";
    }
}

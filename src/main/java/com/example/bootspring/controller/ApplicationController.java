package com.example.bootspring.controller;

import com.example.bootspring.entity.Application;
import com.example.bootspring.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping({"/listApplication", "/"})
    public ModelAndView getAllApplication() {
        ModelAndView mav = new ModelAndView("list/list-application");
        mav.addObject("applications",applicationRepository.findAll());
        return mav;
    }

    @GetMapping("/addApplicationForm")
    public ModelAndView addApplicationForm() {
        ModelAndView mav = new ModelAndView("add/addApplication");
        Application newApplication = new Application();
        mav.addObject("application", newApplication);
        return mav;
    }

    @GetMapping("/updateApplicationForm")
    public ModelAndView updateApplicationForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("update/updateApplication");
        Application update = applicationRepository.getId(id);
        mav.addObject("update", update);
        return mav;
    }

    @Transactional // транзакция для обновления методово delete/update
    @PostMapping("/updateApplication")
    public String updateApplication(Long id, String firstname, String lastname, Long age, String address,
                                    Long status) {
        applicationRepository.updateApplication(id, firstname, lastname, age, address, status);
        return "redirect:/application/listApplication";
    }

    @GetMapping("/findApplicationForm")
    public ModelAndView findApplicationForm() {
        ModelAndView mav = new ModelAndView("find/findApplication");
        Application newApplication = new Application();
        mav.addObject("find", newApplication);
        return mav;
    }

    @GetMapping("/findApplication")
    public ModelAndView findApplication(@RequestParam String firstname) {
        ModelAndView mav = new ModelAndView("find/finderApplication");
        mav.addObject("finder", applicationRepository.findByName(firstname));
        return mav;
    }

    @PostMapping("/saveApplication")
    public String saveApplication(@Valid Application application, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add/addApplication";
        }
        applicationRepository.save(application);
        model.addAttribute("peoples", applicationRepository.findAll());
        return "redirect:/application/listApplication";
    }
}

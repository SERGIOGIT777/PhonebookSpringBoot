package com.example.bootspring.controller;

import com.example.bootspring.entity.Phonebook;
import com.example.bootspring.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/phonebook")
public class PhonebookController {
    @Autowired
    private PhonebookRepository phonebookRepository;

    @GetMapping({"/listPhone", "/"})
    public ModelAndView getAllPhonebooks() {
        ModelAndView mav = new ModelAndView("list-phonebook");
        mav.addObject("phonebooks",phonebookRepository.findAll());
        return mav;
    }

    @GetMapping("/addPhonebookForm")
    public ModelAndView addPhonebookForm() {
        ModelAndView mav = new ModelAndView("addPhonebook");
        Phonebook newPhonebook = new Phonebook();
        mav.addObject("phonebook", newPhonebook);
        return mav;
    }

    @PostMapping("/savePhonebook")
    public String savePhonebook(@ModelAttribute Phonebook phonebook) {
        phonebookRepository.save(phonebook);
        return "redirect:/phonebook/listPhone";
    }
}

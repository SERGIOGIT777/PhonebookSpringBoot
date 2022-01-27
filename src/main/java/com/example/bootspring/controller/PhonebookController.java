package com.example.bootspring.controller;

import com.example.bootspring.entity.Phonebook;
import com.example.bootspring.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/phonebook")
public class PhonebookController {
    @Autowired
    private PhonebookRepository phonebookRepository;

    @GetMapping("/listPhone")
    @Modifying
    public ModelAndView getAllPhonebooks() {
        ModelAndView mav = new ModelAndView("list/list-phonebook");
        mav.addObject("phonebooks",phonebookRepository.findAll());
        return mav;
    }

    @GetMapping("/addPhonebookForm")
    public ModelAndView addPhonebookForm() {
        ModelAndView mav = new ModelAndView("add/addPhonebook");
        Phonebook newPhonebook = new Phonebook();
        mav.addObject("phonebook", newPhonebook);
        return mav;
    }

    @GetMapping("/updatePhonebookForm")
    public ModelAndView updatePhonebookForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("update/updatePhonebook");
        Phonebook update = phonebookRepository.getId(id);
        mav.addObject("update", update);
        return mav;
    }

    @Transactional // транзакция для обновления методово delete/update
    @PostMapping("/updatePhonebook")
    public String updatePhonebook(Long id, String firstname, String lastname, Long age, String phone_number,
                                  String address) {
        phonebookRepository.updateApplication(id, firstname, lastname, age, phone_number, address);
        return "redirect:/phonebook/listPhone";
    }

    @GetMapping("/findPhonebookForm")
    public ModelAndView findPhonebookForm() {
        ModelAndView mav = new ModelAndView("find/findPhonebook");
        Phonebook newPhonebook = new Phonebook();
        mav.addObject("find", newPhonebook);
        return mav;
    }

    @GetMapping("/findPhonebook")
    public ModelAndView findPhonebook(@RequestParam String firstname) {
        ModelAndView mav = new ModelAndView("find/finderPhonebook");
        mav.addObject("finder", phonebookRepository.findByName(firstname));
        return mav;
    }

    @PostMapping("/savePhonebook")
    public String savePhonebook(@Valid Phonebook phonebook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add/addPhonebook";
        }
        phonebookRepository.save(phonebook);
        model.addAttribute("users", phonebookRepository.findAll());
        return "redirect:/phonebook/listPhone";
    }

    @DeleteMapping("/deleteMappingPhone")
    @Transactional
    public String deleteMapping(@RequestParam Long id) {
        phonebookRepository.deleteById(id);
        return "redirect:/phonebook/listPhone";
    }
}

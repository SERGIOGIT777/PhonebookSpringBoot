package com.example.bootspring.controller;

import com.example.bootspring.entity.Phonebook;
import com.example.bootspring.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PhonebookRepository phonebookRepository;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    @GetMapping("/index")
    public String index() {
        return "Index page";
    }

    @GetMapping("/index2/{param1}")
    public String index2(@PathVariable("param1") String param1) {
        return "Index page: " + param1;
    }

    @GetMapping("/phonebook")
    @ResponseBody
    public String phonebook() {
        var numbers = new LinkedList<>();
        this.phonebookRepository.findAll().forEach(pb -> numbers.add(pb.toString()));
        return "Phonebook firstnames: " + numbers;
    }

    @GetMapping("/phonebook/json")
    @ResponseBody
    public List<Phonebook> phonebookJSON() {
        var numbers = new LinkedList<Phonebook>();
        this.phonebookRepository.findAll().forEach(numbers::add);
        return numbers;
    }
}

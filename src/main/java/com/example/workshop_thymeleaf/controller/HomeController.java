package com.example.workshop_thymeleaf.controller;

import com.example.workshop_thymeleaf.Repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    public HomeController(){

    }
    @GetMapping("/")
    public String getHome(){

        return "home";
    }
}

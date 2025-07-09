package com.example.xmlprocessor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // O método retorna a view "index".
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
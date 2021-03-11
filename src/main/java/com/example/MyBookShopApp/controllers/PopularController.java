package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.services.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PopularController {

    private final PopularService popularService;

    @Autowired
    public PopularController(PopularService popularService) {
        this.popularService = popularService;
    }

    @GetMapping("/popular")
    public String popularPage() {
        return "books/popular";
    }
}

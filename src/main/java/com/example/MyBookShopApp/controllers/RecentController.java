package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.services.RecentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecentController {

    private final RecentService recentService;

    @Autowired
    public RecentController(RecentService recentService) {
        this.recentService = recentService;
    }

    @GetMapping("/recent")
    public String recentPage() {
        return "books/recent";
    }
}

package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.RecentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RecentController {

    private final RecentService recentService;
    private final BookService bookService;

    @Autowired
    public RecentController(RecentService recentService, BookService bookService) {
        this.recentService = recentService;
        this.bookService = bookService;
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks() {
        return bookService.getPageOfNewBooks(0, 5).getContent();
    }

    @GetMapping("/recent")
    public String recentPage() {
        return "books/recent";
    }
}

package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularController {

    private final PopularService popularService;
    private final BookService bookService;

    @Autowired
    public PopularController(PopularService popularService, BookService bookService) {
        this.popularService = popularService;
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 5).getContent();
    }

    @GetMapping("/popular")
    public String popularPage() {
        return "books/popular";
    }
}

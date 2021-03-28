package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class GenresController {

    private final GenresService genresService;
    private final BookService bookService;

    @Autowired
    public GenresController(GenresService genresService, BookService bookService) {
        this.genresService = genresService;
        this.bookService = bookService;
    }

//    @ModelAttribute("booksByGenre")
//    public List<Book> booksByGenre() {
//        return bookService.getPageBooksByGenre(0, 6).getContent();
//    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }
}

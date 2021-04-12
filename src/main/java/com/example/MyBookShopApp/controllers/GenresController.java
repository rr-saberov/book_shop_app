package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.SearchWordDto;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @ModelAttribute("booksByGenre")
    public List<Book> booksByGenre() {
        return bookService.getBooksByGenre();
    }

//    @ModelAttribute("booksWithGenre")
//    public List<Book> getBooksWithGenre() {
//        return bookService.getBooksWithGenre("");
//    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/genres/slug")
    public String genresSlugPage() {
        return "genres/slug";
    }

    @ResponseBody
    @GetMapping("/books/byGenre/{genre}")
    public String getBooksByGenre(@PathVariable(value = "genre", required = false) SearchWordDto searchWordDto,
                                  Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getBooksPageByGenre(searchWordDto.getExample(), 0, 5).getContent());
        return "/genres/index";
    }
}

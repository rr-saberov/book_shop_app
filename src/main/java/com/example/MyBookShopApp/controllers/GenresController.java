package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.SearchWordDto;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GenresController {

    private final BookService bookService;

    @Autowired
    public GenresController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

    @ResponseBody
    @GetMapping("/books/byGenre/{genre}")
    public String getBooksByGenre(@PathVariable(value = "genre", required = false) SearchWordDto searchWordDto,
                                  Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageBooksByGenre(searchWordDto.getExample(), 0, 5).getContent());
        return "/genres/index";
    }
}

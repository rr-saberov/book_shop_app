package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Author;
import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.entities.SearchWordDto;
import com.example.MyBookShopApp.services.AuthorService;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "authors/index";
    }

    @GetMapping("/authors/slug")
    public String authorsSlugPage() {
        return "genres/slug";
    }

    @ResponseBody
    @GetMapping("/books/authors/{author}")
    public String getBooksByAuthor(@PathVariable(value = "author", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                authorService.getBooksPageByAuthor(searchWordDto.getExample(), 0, 5).getContent());
        return "/books/author";
    }
}

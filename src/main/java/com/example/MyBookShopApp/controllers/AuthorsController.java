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
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getBooksOrderByAuthor();
    }

    @ModelAttribute("recommendedAuthors")
    public List<Author> recommendedAuthors() {
        return authorService.getAuthors();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "authors/index";
    }

    @GetMapping("/authors/slug/{author}")
    public String authorsSlugPage(@PathVariable String author, Model model) {
        model.addAttribute("searchWordDto", bookService.getBooksByAuthor(author));
        return "authors/slug";
    }

    @GetMapping("/books/author/{author}")
    public String authorsBooks(@PathVariable String author, Model model) {
        model.addAttribute("searchWordDto", bookService.getBooksByAuthor(author));
        return "books/author";
    }
}
